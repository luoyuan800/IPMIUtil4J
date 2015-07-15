/*
 * AbsertRequest.java
 * Date: 7/15/2015
 * Time: 8:53 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.IPMIClient;
import client.LocalIPMIClient;
import param.AuthenticationType;
import param.DriverType;
import param.PrivilegeLevel;

public abstract class AbstractRequest implements IPMIRequest{
    private DriverType driverType;
    private AuthenticationType authenticationType;
    private PrivilegeLevel privilegeLevel;

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public PrivilegeLevel getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(PrivilegeLevel privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public abstract String getCommandString();

    public String buildCommand(IPMIClient client){
        StringBuilder sb = new StringBuilder(client.getIPMI_META_COMMAND()).append(" ").append(getCommandString());
        if(client instanceof LocalIPMIClient){
            return sb.toString();
        }else{
            sb.append(" -N ").append(client.getHost()).append(" -U ").append(client.getUser()).append(" -P ").append(client.getPassword()).append(" -J ").append(client.getCs().getId());
            if(authenticationType!=null){
                sb.append(" -T ").append(authenticationType.getIndex());
            }
            if(privilegeLevel!=null){
                sb.append(" -V ").append(privilegeLevel.getLevel());
            }
            if(driverType!=null){
                sb.append(" -F ").append(driverType.name());
            }
            return sb.toString();
        }
    }
}
