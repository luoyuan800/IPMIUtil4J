package client;

import param.CipherSuite;

public class LocalIPMIClient extends IPMIClient {

	public LocalIPMIClient() {
		super("127.0.0.1","", "", null, null);
	}

}
