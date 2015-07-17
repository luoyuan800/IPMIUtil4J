/*
 * SensorRespond.java
 * Date: 7/15/2015
 * Time: 10:39 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package respond;

import model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorRespond implements IPMIRespond {
    private List<Sensor> sensors;
    private boolean success;
    @Override
    public boolean hasRespond() {
        return success;
    }

    public synchronized void addSensor(Sensor sensor){
        if(sensors==null){
            sensors = new ArrayList<Sensor>();
        }
        sensors.add(sensor);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
