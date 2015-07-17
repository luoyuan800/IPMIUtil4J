/*
 * TestAlarmsRequest.java
 * Date: 7/17/2015
 * Time: 9:47 AM
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
import respond.AlarmRespond;
import respond.ChassisStatusRespond;

import java.io.IOException;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ReadFileAsInput.readFile;

public class TestAlarmsRequest {
    @Test
    public void testAlarms() throws IOException {
        Command command = mock(Command.class);
        when(command.exeCmd(contains("ipmiutil alarms"))).thenReturn(readFile("../data/alarms-output"));
        LocalIPMIClient client = new LocalIPMIClient(Platform.Win64);
        AlarmsRequest request = new AlarmsRequest(command);
        AlarmRespond respond = request.sendTo(client);
        Assert.assertTrue(respond.hasRespond());
        Assert.assertEquals(respond.getAlarmLEDCritical(), false);
        Assert.assertSame(respond.getAlarmLEDMajor(), false);
        Assert.assertEquals(respond.getAlarmRelayMajor(), false);
        Assert.assertEquals(respond.getAlarms().size(), 6);
    }
}
