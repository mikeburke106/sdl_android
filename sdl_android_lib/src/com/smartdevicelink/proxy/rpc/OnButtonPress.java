package com.smartdevicelink.proxy.rpc;

import org.json.JSONObject;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCNotification;
import com.smartdevicelink.proxy.rpc.enums.ButtonName;
import com.smartdevicelink.proxy.rpc.enums.ButtonPressMode;
import com.smartdevicelink.proxy.rpc.enums.SdlCommand;
import com.smartdevicelink.util.JsonUtils;

/**
 * <p>
 * Notifies application of button press events for buttons to which the
 * application is subscribed. SDL supports two button press events defined as
 * follows:
 * </p>
 * <ul>
 * <li>SHORT - Occurs when a button is depressed, then released within two
 * seconds. The event is considered to occur immediately after the button is
 * released.</li>
 * <li>LONG - Occurs when a button is depressed and held for two seconds or
 * more. The event is considered to occur immediately after the two second
 * threshold has been crossed, before the button is released</li>
 * </ul>
 * <b>HMI Status Requirements:</b>
 * <ul>
 * HMILevel:
 * <ul>
 * <li>The application will receive OnButtonPress notifications for all
 * subscribed buttons when HMILevel is FULL.</li>
 * <li>The application will receive OnButtonPress notifications for subscribed
 * media buttons when HMILevel is LIMITED.</li>
 * <li>Media buttons include SEEKLEFT, SEEKRIGHT, TUNEUP, TUNEDOWN, and
 * PRESET_0-PRESET_9.</li>
 * <li>The application will not receive OnButtonPress notification when HMILevel
 * is BACKGROUND or NONE.</li>
 * </ul>
 * AudioStreamingState:
 * <ul>
 * <li> Any </li>
 * </ul>
 * SystemContext:
 * <ul>
 * <li>MAIN, VR. In MENU, only PRESET buttons. In VR, pressing any subscribable
 * button will cancel VR.</li>
 * </ul>
 * </ul>
 * <p>
 * <b>Parameter List:</b>
 * <table  border="1" rules="all">
 * <tr>
 * <th>Name</th>
 * <th>Type</th>
 * <th>Description</th>
 * <th>Req</th>
 * <th>Notes</th>
 * <th>SmartDeviceLink Ver Available</th>
 * </tr>
 * <tr>
 * <td>buttonName</td>
 * <td>{@linkplain ButtonName}</td>
 * <td>Name of the button which triggered this event</td>
 * <td></td>
 * <td></td>
 * <td>SmartDeviceLink 1.0</td>
 * </tr>
 * <tr>
 * <td>buttonPressMode</td>
 * <td>{@linkplain ButtonPressMode}</td>
 * <td>Indicates whether this is an SHORT or LONG button press event.</td>
 * <td></td>
 * <td></td>
 * <td>SmartDeviceLink 1.0</td>
 * </tr>
 * <tr>
 * <td>customButtonID</td>
 * <td>Integer</td>
 * <td>If ButtonName is ��CUSTOM_BUTTON", this references the integer ID passed
 * by a custom button. (e.g. softButton ID)</td>
 * <td>N</td>
 * <td>Minvalue=0 Maxvalue=65536</td>
 * <td>SmartDeviceLink 2.0</td>
 * </tr>
 * </table>
 * </p>
 * 
 * @since SmartDeviceLink 1.0
 * @see SubscribeButton
 * @see UnsubscribeButton
 */
public class OnButtonPress extends RPCNotification {
	public static final String KEY_BUTTON_PRESS_MODE = "buttonPressMode";
	public static final String KEY_BUTTON_NAME = "buttonName";
	public static final String KEY_CUSTOM_BUTTON_ID = "customButtonID";
    
    private String buttonName; // represents ButtonName enum
    private String buttonPressMode; // represents ButtonPressMode enum
    private Integer customButtonId;
    
	/**
	*Constructs a newly allocated OnButtonPress object
	*/   
    public OnButtonPress() {
        super(FunctionID.ON_BUTTON_PRESS);
    }
    
    /**
     * Creates an OnButtonPress object from a JSON object.
     * 
     * @param jsonObject The JSON object to read from
     */    
    public OnButtonPress(JSONObject jsonObject) {
        super(SdlCommand.ON_BUTTON_PRESS, jsonObject);
        jsonObject = getParameters(jsonObject);
        switch(sdlVersion){
        default:
            this.buttonName = JsonUtils.readStringFromJsonObject(jsonObject, KEY_BUTTON_NAME);
            this.buttonPressMode = JsonUtils.readStringFromJsonObject(jsonObject, KEY_BUTTON_PRESS_MODE);
            this.customButtonId = JsonUtils.readIntegerFromJsonObject(jsonObject, KEY_CUSTOM_BUTTON_ID);
            break;
        }
    }
    
    /**
     * <p>Returns an <i>{@linkplain ButtonName}</i> the button's name</p>
     * @return ButtonName Name of the button
     */    
    public ButtonName getButtonName() {
        return ButtonName.valueForJsonName(this.buttonName, sdlVersion);
    }
    
    /**
     * <p>Set the button's name</p>    
     * @param buttonName name of the button
     */    
    public void setButtonName( ButtonName buttonName ) {
        this.buttonName = (buttonName == null) ? null : buttonName.getJsonName(sdlVersion);
    }
    
    /**<p>Returns <i>{@linkplain ButtonPressMode}</i></p>
     * @return ButtonPressMode whether this is a long or short button press event
     */    
    public ButtonPressMode getButtonPressMode() {
        return ButtonPressMode.valueForJsonName(this.buttonPressMode, sdlVersion);
    }
    
    /**
     * <p>Set the button press mode of the event</p>
     * @param buttonPressMode indicates whether this is a short or long press
     */    
    public void setButtonPressMode( ButtonPressMode buttonPressMode ) {
        this.buttonPressMode = (buttonPressMode == null) ? null : buttonPressMode.getJsonName(sdlVersion);
    }
    
    public void setCustomButtonName(Integer customButtonID) {
    	this.customButtonId = customButtonID;
    }
    
    public Integer getCustomButtonName() {
    	return this.customButtonId;
    }

    @Override
    public JSONObject getJsonParameters(int sdlVersion){
        JSONObject result = super.getJsonParameters(sdlVersion);
        
        switch(sdlVersion){
        default:
            JsonUtils.addToJsonObject(result, KEY_BUTTON_NAME, this.buttonName);
            JsonUtils.addToJsonObject(result, KEY_BUTTON_PRESS_MODE, this.buttonPressMode);
            JsonUtils.addToJsonObject(result, KEY_CUSTOM_BUTTON_ID, this.customButtonId);
            break;
        }
        
        return result;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buttonName == null) ? 0 : buttonName.hashCode());
		result = prime * result + ((buttonPressMode == null) ? 0 : buttonPressMode.hashCode());
		result = prime * result + ((customButtonId == null) ? 0 : customButtonId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { 
			return true;
		}
		if (obj == null) { 
			return false;
		}
		if (getClass() != obj.getClass()) { 
			return false;
		}
		OnButtonPress other = (OnButtonPress) obj;
		if (buttonName == null) {
			if (other.buttonName != null) { 
				return false;
			}
		}
		else if (!buttonName.equals(other.buttonName)) { 
			return false;
		}
		if (buttonPressMode == null) {
			if (other.buttonPressMode != null) { 
				return false;
			}
		}
		else if (!buttonPressMode.equals(other.buttonPressMode)) { 
			return false;
		}
		if (customButtonId == null) {
			if (other.customButtonId != null) { 
				return false;
			}
		} 
		else if (!customButtonId.equals(other.customButtonId)) { 
			return false;
		}
		return true;
	}
}
