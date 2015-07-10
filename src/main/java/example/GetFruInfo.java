/*
 * GetFruInfo.java
 * Date: 7/10/2015
 * Time: 11:04 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package example;

import client.IPMIClient;
import param.CipherSuite;
import param.Platform;
import request.FruRequest;

public class GetFruInfo {
        public static void main(String[]args){
            IPMIClient client = new IPMIClient("10.4.33.146", "Adminstrator", "rdis2fun", CipherSuite.cs3, Platform.Win64 );
            FruRequest fruRequest = new FruRequest();
            fruRequest.sendTo(client);
            //System.out.println("Is device reacherable by IPMI? " + (client.verify() ? "Yes!" : "No!"));
        }
}
