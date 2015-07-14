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
import org.junit.Assert;
import org.junit.Test;
import param.Platform;
import respond.FruRespond;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testFru {

    @Test
    public void testFruRequest() throws IOException {
        Command command = mock(Command.class);
        when(command.exeCmd(anyString())).thenReturn(readFile("../data/fru-output"));
        LocalIPMIClient client = new LocalIPMIClient(Platform.Win64);
        FruRequest request = new FruRequest();
        request.setCommand(command);
        FruRespond respond = request.sendTo(client);
        Assert.assertTrue(respond.hasResponsed());
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
