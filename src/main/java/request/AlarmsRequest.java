/*
 * AlarmsRequest.java
 * Date: 7/17/2015
 * Time: 8:41 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.IPMIClient;
import command.Command;
import param.Platform;
import respond.AlarmRespond;
import respond.IPMIRespond;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlarmsRequest extends AbstractRequest {
    private boolean readOnly = true;
    private Boolean allAlarmsOff;
    private Integer chassisID;
    private Boolean diskAFaultLEDOff;
    private Boolean diskBFaultLEDOff;
    private Boolean diskXFaultLEDOff;
    private Boolean criticalAlarmOff;
    private Boolean majorAlarmOff;
    private Boolean minorAlarmOff;
    private Boolean powerAlarmOff;
    public AlarmsRequest(Command command){
        super(command);
    }
    public AlarmsRequest(){
        super();
    }
    private static final Pattern
            ledP = Pattern.compile("^Alarm LEDs:   critical = (off|on) major = (off|on) minor = (off|on) power = (off|on)$"),
            relayP = Pattern.compile("^Alarm Relays: major = (off|on)  minor = (on|off)$"),
            statusP = Pattern.compile("^(.*):   (on|off)$"),
            endP = Pattern.compile("^alarms, completed successfully$");

    @Override
    public String getCommandString() {
        return "alarms";
    }

    @Override
    public AlarmRespond sendTo(IPMIClient client) {
        AlarmRespond alarmRespond = new AlarmRespond();
        for (String line : command.exeCmd(buildCommand(client))) {
            Matcher matcher = ledP.matcher(line);
            if(matcher.find()){
                alarmRespond.setAlarmLEDCritical(matcher.group(1).matches("on"));
                alarmRespond.setAlarmLEDMajor(matcher.group(2).matches("on"));
                alarmRespond.setAlarmLEDMinor(matcher.group(3).matches("on"));
                alarmRespond.setAlarmLEDPower(matcher.group(4).matches("on"));
                continue;
            }
            matcher = relayP.matcher(line);
            if(matcher.find()){
                alarmRespond.setAlarmRelayMajor(matcher.group(1).matches("on"));
                alarmRespond.setAlarmRelayMinor(matcher.group(2).matches("on"));
                continue;
            }
            matcher = endP.matcher(line);
            if(matcher.find()){
                alarmRespond.setSuccess(true);
                break;
            }
            matcher = statusP.matcher(line);
            if(matcher.find()){
                AlarmRespond.Alarm alarm = new AlarmRespond.Alarm();
                alarm.setStatus(matcher.group(2).matches("on"));
                alarm.setName(matcher.group(1));
                alarmRespond.addAlarm(alarm);
            }
        }
        return alarmRespond;
    }

    public String buildCommand(IPMIClient client) {
        String com = super.buildCommand(client);
        if (!readOnly) {
            StringBuilder builder = new StringBuilder(com);
            if (allAlarmsOff != null && allAlarmsOff) {
                builder.append(" -o");
            } else if (allAlarmsOff != null) {
                builder.append(" -i255").append(" -c1").append("- m1").append(" -n1").append(" -p1");
                if (client.getPlatform() == Platform.TIGPT1U) {
                    builder.append(" -a1").append(" -b1");
                } else if (client.getPlatform() == Platform.NSC2U) {
                    builder.append(" -dx1");
                }

            } else {
                if (client.getPlatform() == Platform.TIGPT1U) {
                    if (diskAFaultLEDOff != null && diskAFaultLEDOff) {
                        builder.append(" -a0");
                    }
                    if (diskAFaultLEDOff != null && !diskAFaultLEDOff) {
                        builder.append(" -a1");
                    }
                    if (diskBFaultLEDOff != null && diskBFaultLEDOff) {
                        builder.append(" -b0");
                    }
                    if (diskBFaultLEDOff != null && !diskBFaultLEDOff) {
                        builder.append(" -b1");
                    }
                }
                if (client.getPlatform() == Platform.NSC2U && diskXFaultLEDOff != null) {
                    if (diskXFaultLEDOff) {
                        builder.append(" -dx0");
                    } else {
                        builder.append(" -dx1");
                    }
                }
                if (chassisID != null) {
                    builder.append(" -i").append(chassisID);
                }
                if (criticalAlarmOff != null) {
                    builder.append(" -c").append(criticalAlarmOff ? "0" : "1");
                }
                if (majorAlarmOff != null) {
                    builder.append(" -m").append(majorAlarmOff ? "0" : "1");
                }
                if (minorAlarmOff != null) {
                    builder.append(" -n").append(minorAlarmOff ? "0" : "1");
                }
                if (powerAlarmOff != null) {
                    builder.append(" -p").append(powerAlarmOff ? "0" : "1");
                }
            }
            return builder.toString();
        }
        return com;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setAllAlarmsOff(boolean allAlarmsOff) {
        this.allAlarmsOff = allAlarmsOff;
    }

    public void setChassisID(Integer chassisID) {
        this.chassisID = chassisID;
    }

    public void setDiskAFaultLEDOff(boolean diskAFaultLEDOff) {
        this.diskAFaultLEDOff = diskAFaultLEDOff;
    }

    public void setDiskBFaultLEDOff(boolean diskBFaultLEDOff) {
        this.diskBFaultLEDOff = diskBFaultLEDOff;
    }

    public void setDiskXFaultLEDOff(boolean diskXFaultLEDOff) {
        this.diskXFaultLEDOff = diskXFaultLEDOff;
    }

    public void setCriticalAlarmOff(boolean criticalAlarmOff) {
        this.criticalAlarmOff = criticalAlarmOff;
    }

    public void setMajorAlarmOff(boolean majorAlarmOff) {
        this.majorAlarmOff = majorAlarmOff;
    }

    public void setMinorAlarmOff(boolean minorAlarmOff) {
        this.minorAlarmOff = minorAlarmOff;
    }

    public void setPowerAlarmOff(boolean powerAlarmOff) {
        this.powerAlarmOff = powerAlarmOff;
    }
}
