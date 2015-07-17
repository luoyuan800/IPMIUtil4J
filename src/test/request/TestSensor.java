/*
 * TestSensor.java
 * Date: 7/15/2015
 * Time: 12:22 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.LocalIPMIClient;
import command.Command;
import org.junit.Assert;
import org.junit.Test;
import param.Platform;
import respond.SensorRespond;

import java.io.IOException;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ReadFileAsInput.*;

public class TestSensor {
    @Test
    public void testSensor() throws IOException {
        Command command = mock(Command.class);
        when(command.exeCmd(contains("ipmiutil sensor"))).thenReturn(readFile("../data/sensor-output"));
        LocalIPMIClient client = new LocalIPMIClient(Platform.Win64);
        SensorRequest request = new SensorRequest();
        request.setCommand(command);
        SensorRespond respond = request.sendTo(client);
        Assert.assertTrue(respond.hasRespond());
    }

}
