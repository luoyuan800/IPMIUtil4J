package util;/*
 * util.ReadFileAsInput.java
 * Date: 7/15/2015
 * Time: 12:24 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

import command.OutputResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileAsInput {
    public static OutputResult readFile(String path) throws IOException {
        OutputResult or = new OutputResult();
        BufferedReader br = new BufferedReader((new InputStreamReader(ReadFileAsInput.class.getResourceAsStream(path))));
        String line;
        while ((line = br.readLine()) != null) {
            or.add(line);
        }
        return or;
    }
}
