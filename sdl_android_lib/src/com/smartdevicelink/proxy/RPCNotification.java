/**
 * 
 */
package com.smartdevicelink.proxy;

import org.json.JSONObject;

import com.smartdevicelink.proxy.rpc.enums.SdlCommand;

/**
 * An RPC request is a type of message that, when sent does not receive a response.
 *
 * @author Mike Burke
 *
 */
public abstract class RPCNotification extends RPCMessage {

	public RPCNotification(String functionName) {
		super(functionName, KEY_NOTIFICATION);
	}

	public RPCNotification(RPCMessage rpcMsg) {
		super(rpcMsg);
	}
	
	public RPCNotification(SdlCommand commandType, JSONObject jsonObject){
	    super(KEY_NOTIFICATION, commandType, jsonObject);
	}
    
    public static JSONObject getParameters(JSONObject json){
        return RPCMessage.getParameters(KEY_NOTIFICATION, json);
    }
}