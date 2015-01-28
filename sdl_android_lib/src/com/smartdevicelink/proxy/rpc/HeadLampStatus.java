package com.smartdevicelink.proxy.rpc;

import org.json.JSONObject;

import com.smartdevicelink.proxy.RPCObject;
import com.smartdevicelink.proxy.rpc.enums.AmbientLightStatus;
import com.smartdevicelink.util.JsonUtils;

public class HeadLampStatus extends RPCObject {
	public static final String KEY_AMBIENT_LIGHT_SENSOR_STATUS = "ambientLightSensorStatus";
	public static final String KEY_HIGH_BEAMS_ON = "highBeamsOn";
    public static final String KEY_LOW_BEAMS_ON = "lowBeamsOn";

    private String ambientLightStatus; // represents AmbientLightStatus enum
    private Boolean highBeamsOn, lowBeamsOn;
    
    public HeadLampStatus() {}
    
    /**
     * Creates a HeadLampStatus object from a JSON object.
     * 
     * @param jsonObject The JSON object to read from
     */
    public HeadLampStatus(JSONObject jsonObject) {
        switch(sdlVersion){
        default:
            this.ambientLightStatus = JsonUtils.readStringFromJsonObject(jsonObject, KEY_AMBIENT_LIGHT_SENSOR_STATUS);
            this.highBeamsOn = JsonUtils.readBooleanFromJsonObject(jsonObject, KEY_HIGH_BEAMS_ON);
            this.lowBeamsOn = JsonUtils.readBooleanFromJsonObject(jsonObject, KEY_LOW_BEAMS_ON);
            break;
        }
    }
    
    public void setAmbientLightStatus(AmbientLightStatus ambientLightSensorStatus) {
        this.ambientLightStatus = ambientLightSensorStatus.getJsonName(sdlVersion);
    }
    
    public AmbientLightStatus getAmbientLightStatus() {
        return AmbientLightStatus.valueForJsonName(this.ambientLightStatus, sdlVersion);
    }
    
    public void setHighBeamsOn(Boolean highBeamsOn) {
        this.highBeamsOn = highBeamsOn;
    }
    
    public Boolean getHighBeamsOn() {
    	return this.highBeamsOn;
    }
    
    public void setLowBeamsOn(Boolean lowBeamsOn) {
        this.lowBeamsOn = lowBeamsOn;
    }
    
    public Boolean getLowBeamsOn() {
    	return this.lowBeamsOn;
    }
}
