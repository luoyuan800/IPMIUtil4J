/*
 * ResetRequest.java
 * Date: 7/16/2015
 * Time: 4:27 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.IPMIClient;
import command.Command;
import command.OutputResult;
import respond.IPMIRespond;
import utils.StringUtils;

import java.util.regex.Pattern;

public class ResetRequest extends AbstractRequest {
    private boolean powerCycleSystem;
    private boolean powerDownSystem;
    private boolean PowerUpSystem;
    private boolean hardRestSystem;
    private boolean coldResetBMC;
    private boolean hardRestBIOS;
    private boolean hardRestEFI;
    private boolean hardResetFloppy;
    private boolean hardResetHardDisk;
    private boolean hardResetDVD;
    private boolean hardResetPXE;
    private boolean senNMI;
    private Command command = new Command();
    private String bootInitiatorMailboxString;
    private String mIANANumber;
    private static final Pattern endP = Pattern.compile("hwreset, completed successfully");

    @Override
    public String getCommandString() {
        return "reset";
    }

    @Override
    public IPMIRespond sendTo(IPMIClient client) {
        OutputResult or = command.exeCmd(buildResetCommand(client));
        for(String line : or){
            if(endP.matcher(line).find()){
                return new IPMIRespond() {
                    @Override
                    public boolean hasRespond() {
                        return true;
                    }
                };
            }
        }
        return new IPMIRespond() {
            @Override
            public boolean hasRespond() {
                return false;
            }
        };
    }

    private String buildResetCommand(IPMIClient client){
        String command = buildCommand(client);
        if(powerCycleSystem) return command + " -c";
        if(powerDownSystem) return  command + " -d";
        if(PowerUpSystem) return  command + " -u";
        if(hardRestSystem) return  command + " -r";
        if(coldResetBMC) return  command + " -k";
        if(hardRestBIOS) return  command + " -b";
        if(hardRestEFI) return  command + " -e";
        if(hardResetFloppy) return  command + " -f";
        if(hardResetHardDisk) return  command + " -h";
        if(hardResetDVD) return  command + " -v";
        if(hardResetPXE) return  command + " -p";
        if(senNMI) return command + " -n";
        if(StringUtils.isNotEmpty(bootInitiatorMailboxString)) return  command + " -i " + bootInitiatorMailboxString;
        if(StringUtils.isNotEmpty(mIANANumber)) return  command + " -j " + mIANANumber;
        return "";
    }

    public void setIANANumber(String mIANANumber) {
        this.mIANANumber = mIANANumber;
    }

    public void setPowerCycleSystem(boolean powerCycleSystem) {
        this.powerCycleSystem = powerCycleSystem;
    }

    public void setPowerDownSystem(boolean powerDownSystem) {
        this.powerDownSystem = powerDownSystem;
    }

    public void setPowerUpSystem(boolean powerUpSystem) {
        PowerUpSystem = powerUpSystem;
    }

    public void setHardRestSystem(boolean hardRestSystem) {
        this.hardRestSystem = hardRestSystem;
    }

    public void setColdResetBMC(boolean coldResetBMC) {
        this.coldResetBMC = coldResetBMC;
    }

    public void setHardRestBIOS(boolean hardRestBIOS) {
        this.hardRestBIOS = hardRestBIOS;
    }

    public void setHardRestEFI(boolean hardRestEFI) {
        this.hardRestEFI = hardRestEFI;
    }

    public void setHardResetFloppy(boolean hardResetFloppy) {
        this.hardResetFloppy = hardResetFloppy;
    }

    public void setHardResetHardDisk(boolean hardResetHardDisk) {
        this.hardResetHardDisk = hardResetHardDisk;
    }

    public void setHardResetDVD(boolean hardResetDVD) {
        this.hardResetDVD = hardResetDVD;
    }

    public void setHardResetPXE(boolean hardResetPXE) {
        this.hardResetPXE = hardResetPXE;
    }

    public void setBootInitiatorMailboxString(String bootInitiatorMailboxString) {
        this.bootInitiatorMailboxString = bootInitiatorMailboxString;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setSenNMI(boolean senNMI) {
        this.senNMI = senNMI;
    }
}
