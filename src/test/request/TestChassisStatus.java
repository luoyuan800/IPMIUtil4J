/*
 * TestChassisStatus.java
 * Date: 7/15/2015
 * Time: 9:42 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.LocalIPMIClient;
import command.Command;
import command.OutputResult;
import org.junit.Assert;
import org.junit.Test;
import param.Platform;
import respond.ChassisStatusRespond;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestChassisStatus {
    @Test
    public void testHealthCommand() throws IOException {
        Command command = mock(Command.class);
        when(command.exeCmd(contains("ipmiutil health"))).thenReturn(readFile("../data/health-output"));
        LocalIPMIClient client = new LocalIPMIClient(Platform.Win64);
        ChassisStatusRequest request = new ChassisStatusRequest();
        request.setCommand(command);
        ChassisStatusRespond respond = request.sendTo(client);
        Assert.assertTrue(respond.hasResponsed());
        Assert.assertEquals(respond.getSelftest(), "OK");
        Assert.assertSame(respond.isPowerOn(), true);
        Assert.assertEquals(respond.getPowerRestorePolicy(), "stay_off");
    }

    private OutputResult readFile(String path) throws IOException {
        OutputResult or = new OutputResult();
        BufferedReader br = new BufferedReader((new InputStreamReader(this.getClass().getResourceAsStream(path))));
        String line;
        while ((line = br.readLine()) != null) {
            or.add(line);
        }
        return or;
    }
}
