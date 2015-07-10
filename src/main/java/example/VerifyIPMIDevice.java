package example;

import param.CipherSuite;
import client.IPMIClient;
import param.Platform;

public class VerifyIPMIDevice {
	public static void main(String[]args){
		IPMIClient client = new IPMIClient("10.4.33.146", "Adminstrator", "rdis2fun", CipherSuite.cs3, Platform.Win64 );
		System.out.println("Is device reacherable by IPMI? " + (client.verify() ? "Yes!" : "No!"));
	}
}
