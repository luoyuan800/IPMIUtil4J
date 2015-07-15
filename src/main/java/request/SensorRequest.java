/*
 * SensorRequest.java
 * Date: 7/15/2015
 * Time: 10:28 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.IPMIClient;
import command.Command;
import command.OutputResult;
import model.Sensor;
import param.SDRType;
import respond.SensorRespond;
import utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensorRequest extends AbstractRequest {
    private Command command = new Command();

    private static final Pattern sensorIDTypeReadingP = Pattern.compile("^([1234567890abcdef]+) SDR (\\w*) [1234567890abcdef].* snum .{2} (.*) *= .*(?<=[1234567890abcdef]{2}) (\\w+) +(-*\\d+\\.\\d+) (Volts|degrees C|RPM|Amps|Watts|unspecified)$"),
            sensorIDTypeStatus = Pattern.compile("^([1234567890abcdef]+) SDR (\\w*) [1234567890abcdef].* snum .{2} (.*) *= .* (\\w*)$"),
            sensorIDType = Pattern.compile("^([1234567890abcdef]+) SDR (\\w*).*"),
            endP = Pattern.compile("sensor, completed successfully");

    @Override
    public String getCommandString() {
        return "sensor";
    }

    @Override
    public SensorRespond sendTo(IPMIClient client) {
        OutputResult or = command.exeCmd(buildCommand(client));
        SensorRespond sr = new SensorRespond();
        if (or != null && or.isNotEmpty()) {
            for (String line : or) {
                if (StringUtils.isNotEmpty(line)) {
                    Matcher matcher = sensorIDTypeReadingP.matcher(line);
                    if (matcher.find()) {
                        Sensor sensor = new Sensor();
                        sensor.setId(matcher.group(1));
                        sensor.setType(SDRType.valueOf(matcher.group(2)));
                        sensor.setDescription(matcher.group(3));
                        sensor.setStatus(matcher.group(4));
                        sensor.setReading(Double.parseDouble(matcher.group(5)));
                        sensor.setUnit(matcher.group(6));
                        sr.addSensor(sensor);
                        continue;
                    }
                    matcher = sensorIDTypeStatus.matcher(line);
                    if (matcher.find()) {
                        Sensor sensor = new Sensor();
                        sensor.setId(matcher.group(1));
                        sensor.setType(SDRType.valueOf(matcher.group(2)));
                        sensor.setDescription(matcher.group(3));
                        sensor.setStatus(matcher.group(4));
                        sr.addSensor(sensor);
                        continue;
                    }
                    matcher = sensorIDType.matcher(line);
                    if (matcher.find()) {
                        Sensor sensor = new Sensor();
                        sensor.setId(matcher.group(1));
                        sensor.setType(SDRType.valueOf(matcher.group(2)));
                        sensor.setDescription(matcher.group());
                        sr.addSensor(sensor);
                    }
                    matcher = sensorIDType.matcher(line);
                    if (matcher.find()) {
                        sr.setSuccess(true);
                        break;
                    }
                }
            }
        }
        return sr;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
