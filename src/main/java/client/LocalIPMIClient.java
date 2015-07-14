package client;

import param.CipherSuite;
import param.Platform;

public class LocalIPMIClient extends IPMIClient {

	public LocalIPMIClient(Platform platform) {
		super("127.0.0.1","", "", null, platform);
	}

}
