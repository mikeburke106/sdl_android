package com.smartdevicelink.proxy.rpc;

import org.json.JSONObject;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.util.JsonUtils;

/**
 * Removes a command from the Command Menu
 * <p>
 * <b>HMI Status Requirements:</b><br/>
 * HMILevel: FULL, LIMITED or BACKGROUND<br/>
 * AudioStreamingState: N/A<br/>
 * SystemContext: Should not be attempted when VRSESSION or MENU
 * </p>
 * 
 * @since SmartDeviceLink 1.0
 * @see AddCommand
 * @see AddSubMenu
 * @see DeleteSubMenu
 */
public class DeleteCommand extends RPCRequest {
	public static final String KEY_CMD_ID = "cmdID";

	private Integer cmdId;
	
	/**
	 * Constructs a new DeleteCommand object
	 */
	public DeleteCommand() {
        super(FunctionID.DELETE_COMMAND);
    }
	
    /**
     * Creates a DeleteCommand object from a JSON object.
     * 
     * @param jsonObject The JSON object to read from
     */
	public DeleteCommand(JSONObject jsonObject) {
        super(jsonObject);
        switch(sdlVersion){
        default:
            this.cmdId = JsonUtils.readIntegerFromJsonObject(jsonObject, KEY_CMD_ID);
            break;
        }
    }
	
	/**
	 * Gets the Command ID that identifies the Command to be deleted from
	 * Command Menu
	 * 
	 * @return Integer - Integer value representing Command ID that identifies
	 *         the Command to be deleted from Command Menu
	 */	
    public Integer getCmdID() {
        return this.cmdId;
    }
    
	/**
	 * Sets the Command ID that identifies the Command to be deleted from Command Menu
	 * 
	 * @param cmdID
	 *            an Integer value representing Command ID
	 *            <p>
	 *            <b>Notes: </b>Min Value: 0; Max Value: 2000000000
	 */    
    public void setCmdID( Integer cmdID ) {
        this.cmdId = cmdID;
    }

    @Override
    public JSONObject getJsonParameters(int sdlVersion){
        JSONObject result = super.getJsonParameters(sdlVersion);
        
        switch(sdlVersion){
        default:
            JsonUtils.addToJsonObject(result, KEY_CMD_ID, this.cmdId);
            break;
        }
        
        return result;
    }
}
