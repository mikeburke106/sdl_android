package com.smartdevicelink.proxy.rpc.enums;

import com.smartdevicelink.util.JsonUtils.JsonInterfaces.JsonName;

/**
 * Defines the possible result codes returned by SDL to the application in a
 * Response to a requested operation
 * <p>
 * 
 * @since SmartDeviceLink 1.0
 */
public enum Result implements JsonName {
	/**
	 * The request succeeded
	 */    
	SUCCESS,
	/**
	 * The data sent is invalid. For example:<br/>
	 * <ul>
	 * <li>Invalid Json syntax</li>
	 * <li>Parameters out of bounds (number or enum range)</li>
	 * <li>Mandatory parameters not provided</li>
	 * <li>Parameter provided with wrong type</li>
	 * <li>Invalid characters</li>
	 * <li>Empty string</li>
	 * </ul>
	 */    
	INVALID_DATA,
	/**
	 * The request is not supported by SDL
	 */    
	UNSUPPORTED_REQUEST,
	/**
	 * The system could not process the request because the necessary memory
	 * couldn't be allocated
	 */    
	OUT_OF_MEMORY,
	/**
	 * There are too many requests pending (means that the response has not been
	 * delivered yet). There is a limit of 1000 pending requests at a time
	 */    
	TOO_MANY_PENDING_REQUESTS,
	/**
	 * One of the provided IDs is not valid. For example:<br/>
	 * <ul>
	 * <li>CorrelationID</li>
	 * <li>CommandID</li>
	 * <li>MenuID</li>
	 * </ul>
	 */
	INVALID_ID,
	/**
	 * The provided name or synonym is a duplicate of some already-defined name
	 * or synonym
	 */    
    DUPLICATE_NAME,
	/**
	 * Specified application name is already associated with an active interface
	 * registration. Attempts at doing a second <i>
	 * {@linkplain com.smartdevicelink.proxy.rpc.RegisterAppInterface}</i> on a
	 * given protocol session will also cause this
	 */    
    TOO_MANY_APPLICATIONS,
	/**
	 * SDL does not support the interface version requested by the mobile
	 * application
	 */    
    APPLICATION_REGISTERED_ALREADY,
	/**
	 * The requested language is currently not supported. Might be because of a
	 * mismatch of the currently active language
	 */    
    UNSUPPORTED_VERSION,
	/**
	 * The request cannot be executed because no application interface has been
	 * registered via <i>
	 * {@linkplain com.smartdevicelink.proxy.rpc.RegisterAppInterface}</i>
	 */    
	WRONG_LANGUAGE,
	/**
	 * The request cannot be executed because no application interface has been
	 * registered via <i>
	 * {@linkplain com.smartdevicelink.proxy.rpc.RegisterAppInterface}</i>
	 */
	APPLICATION_NOT_REGISTERED,
	/**
	 * The data may not be changed, because it is currently in use. For example,
	 * when trying to delete a Choice Set that is currently involved in an
	 * interaction
	 */
	IN_USE,
	/**
	 * There is already an existing subscription for this item
	 */
    VEHICLE_DATA_NOT_ALLOWED,
	VEHICLE_DATA_NOT_AVAILABLE,
	/**
	 * The requested operation was rejected. No attempt was made to perform the
	 * operation
	 */
	REJECTED,
	/**
	 * The requested operation was aborted due to some pre-empting event (e.g.
	 * button push, <i>{@linkplain com.smartdevicelink.proxy.rpc.Alert}</i>
	 * pre-empts <i>{@linkplain com.smartdevicelink.proxy.rpc.Speak}</i>, etc.)
	 */
	ABORTED,
	/**
	 * The requested operation was ignored because it was determined to be
	 * redundant (e.g. pause media clock when already paused)
	 */
	IGNORED,
	/**
	 * A button that was requested for subscription is not supported on the
	 * currently connected SDL platform. See DisplayCapabilities for further
	 * information on supported buttons on the currently connected SDL platform
	 */
    UNSUPPORTED_RESOURCE,
    FILE_NOT_FOUND,
    GENERIC_ERROR,
    DISALLOWED,
    USER_DISALLOWED,
    TIMED_OUT,
    CANCEL_ROUTE,
    TRUNCATED_DATA,
    RETRY,
    WARNINGS,
    SAVED,
    INVALID_CERT,
    EXPIRED_CERT,
    RESUME_FAILED,
    
    ;

    public static Result valueForString(String value) {
        return valueOf(value);
    }

    /**
     * Returns the enumerated value for a given string and associated SDL version.
     * 
     * @param name The name of the JSON string
     * @param sdlVersion The SDL version associated with the input string
     * @return The enumerated value for the given string or null if it wasn't found
     */
    public static Result valueForJsonName(String name, int sdlVersion){
        if(name == null){
            return null;
        }

        switch(sdlVersion){
        default:
            return valueForString(name);
        }
    }

    @Override
    public String getJsonName(int sdlVersion){
        switch(sdlVersion){
        default:
            return this.name();
        }
    }
}
