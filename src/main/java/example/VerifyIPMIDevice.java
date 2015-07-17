package example;

import param.CipherSuite;
import client.IPMIClient;
import param.Platform;

/**
 * An example for how to verify if the target device support IPMI
 */
public class VerifyIPMIDevice {
	public static void main(String[]args){
		IPMIClient client = new IPMIClient("10.1.1.146", "Adminstrator", "1234567890", CipherSuite.cs3, Platform.Win64 );
		System.out.println("Is device reacherable by IPMI? " + (client.verify() ? "Yes!" : "No!"));
	}
}
