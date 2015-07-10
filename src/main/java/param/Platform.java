/*
 * Platform.java
 * Date: 7/10/2015
 * Time: 10:39 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/
package param;

public enum Platform {
    Win64("ipmiutil-win64/ipmiutil");

    private String IPMI_META_COMMAND;
    private Platform(String command){
        IPMI_META_COMMAND = command;
    }

    public String getIPMI_META_COMMAND() {
        return IPMI_META_COMMAND;
    }
}
