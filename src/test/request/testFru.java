/*
 * testFru.java
 * Date: 7/14/2015
 * Time: 5:24 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package request;

import client.LocalIPMIClient;
import command.Command;
import command.OutputResult;
import model.ComponentFru;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import param.Platform;
import respond.FruRespond;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ReadFileAsInput.*;

public class TestFru {

    @Test
    public void testFruRequest() throws IOException {
        Command command = mock(Command.class);
        when(command.exeCmd(contains("ipmiutil fru"))).thenReturn(readFile("../data/fru-output"));
        LocalIPMIClient client = new LocalIPMIClient(Platform.Win64);
        FruRequest request = new FruRequest();
        request.setCommand(command);
        FruRespond respond = request.sendTo(client);
        Assert.assertTrue(respond.hasResponsed());
        Assert.assertSame(respond.getFrus(ComponentFru.class).size(), 3);
    }

}
