package com.smartdevicelink.proxy.rpc;

import java.util.Hashtable;
import java.util.List;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.interfaces.BulkData;
import com.smartdevicelink.proxy.rpc.enums.RequestType;
import com.smartdevicelink.util.DebugTool;

public class SystemRequest extends RPCRequest implements BulkData{
	public static final String KEY_FILE_NAME = "fileName";
	public static final String KEY_REQUEST_TYPE = "requestType";
	public static final String KEY_DATA = "data";
	
    public SystemRequest() {
        super(FunctionID.SYSTEM_REQUEST);
    }

	public SystemRequest(boolean bLegacy) {
        super(FunctionID.ENCODED_SYNC_P_DATA);
    }
    
    public SystemRequest(Hashtable<String, Object> hash) {
        super(hash);
    }

    @SuppressWarnings("unchecked")    
    public List<String> getLegacyData() {
        if (parameters.get(KEY_DATA) instanceof List<?>) {
        	List<?> list = (List<?>)parameters.get(KEY_DATA);
        	if (list != null && list.size()>0) {
        		Object obj = list.get(0);
        		if (obj instanceof String) {
        			return (List<String>) list;
        		}
        	}
        }
    	return null;
    }
 
    public void setLegacyData( List<String> data ) {
    	if ( data!= null) {
    		parameters.put(KEY_DATA, data );
    	} else {
            parameters.remove(KEY_DATA);
        }
    }    
            
    public String getFileName() {
        return (String) parameters.get(KEY_FILE_NAME);
    }
    
    public void setFileName(String fileName) {
        if (fileName != null) {
            parameters.put(KEY_FILE_NAME, fileName);
        } else {
        	parameters.remove(KEY_FILE_NAME);
        }
    }    

    public RequestType getRequestType() {
        Object obj = parameters.get(KEY_REQUEST_TYPE);
        if (obj instanceof RequestType) {
            return (RequestType) obj;
        } else if (obj instanceof String) {
            RequestType theCode = null;
            try {
                theCode = RequestType.valueForString((String) obj);
            } catch (Exception e) {
                DebugTool.logError(
                        "Failed to parse " + getClass().getSimpleName() + "." +
                        		KEY_REQUEST_TYPE, e);
            }
            return theCode;
        }
        return null;
    }

    public void setRequestType(RequestType requestType) {
        if (requestType != null) {
            parameters.put(KEY_REQUEST_TYPE, requestType);
        } else {
            parameters.remove(KEY_REQUEST_TYPE);
        }
    }
}
