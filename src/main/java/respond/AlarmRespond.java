/*
 * AlarmRespond.java
 * Date: 7/17/2015
 * Time: 9:16 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package respond;

import java.util.ArrayList;
import java.util.List;

public class AlarmRespond implements IPMIRespond {
    private Boolean alarmLEDCritical;
    private Boolean alarmLEDMajor;
    private Boolean alarmLEDMinor;
    private Boolean alarmLEDPower;
    private Boolean alarmRelayMajor;
    private Boolean alarmRelayMinor;
    private List<Alarm> alarms;
    private boolean success;

    public synchronized void addAlarm(Alarm alarm) {
        if (alarms == null) {
            alarms = new ArrayList<Alarm>();
        }
        alarms.add(alarm);
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    @Override
    public boolean hasRespond() {
        return success;
    }

    public Boolean getAlarmLEDCritical() {
        return alarmLEDCritical;
    }

    public void setAlarmLEDCritical(Boolean alarmLEDCritical) {
        this.alarmLEDCritical = alarmLEDCritical;
    }

    public Boolean getAlarmLEDMajor() {
        return alarmLEDMajor;
    }

    public void setAlarmLEDMajor(Boolean alarmLEDMajor) {
        this.alarmLEDMajor = alarmLEDMajor;
    }

    public Boolean getAlarmLEDMinor() {
        return alarmLEDMinor;
    }

    public void setAlarmLEDMinor(Boolean alarmLEDMinor) {
        this.alarmLEDMinor = alarmLEDMinor;
    }

    public Boolean getAlarmLEDPower() {
        return alarmLEDPower;
    }

    public void setAlarmLEDPower(Boolean alarmLEDPower) {
        this.alarmLEDPower = alarmLEDPower;
    }

    public Boolean getAlarmRelayMajor() {
        return alarmRelayMajor;
    }

    public void setAlarmRelayMajor(Boolean alarmRelayMajor) {
        this.alarmRelayMajor = alarmRelayMajor;
    }

    public Boolean getAlarmRelayMinor() {
        return alarmRelayMinor;
    }

    public void setAlarmRelayMinor(Boolean alarmRelayMinor) {
        this.alarmRelayMinor = alarmRelayMinor;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class Alarm {
        private String name;
        private Boolean status;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
