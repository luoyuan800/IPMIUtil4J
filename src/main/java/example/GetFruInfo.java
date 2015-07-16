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
import model.ComponentFru;
import param.CipherSuite;
import param.Platform;
import request.FruRequest;
import respond.FruRespond;

public class GetFruInfo {
        public static void main(String[]args){
            IPMIClient client = new IPMIClient("10.4.33.146", "Adminstrator", "rdis2fun", CipherSuite.cs3, Platform.Win64 );
            FruRequest fruRequest = new FruRequest();
            FruRespond  respond = fruRequest.sendTo(client);
            for(ComponentFru fru : respond.<ComponentFru>getFrus(ComponentFru.class)){
                System.out.println(fru.getName());
                System.out.println(fru.getProductName());
                System.out.println(fru.getProductManufacturer());
            }
        }
}
