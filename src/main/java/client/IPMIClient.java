package client;

import param.CipherSuite;
import param.Platform;
import request.ChassisStatusRequest;
import respond.ChassisStatusRespond;

public class IPMIClient {
	private String IPMI_META_COMMAND = "";
	private String user;
	private String password;
	private String host;
	private CipherSuite cs;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public CipherSuite getCs() {
		return cs;
	}
	public void setCs(CipherSuite cs) {
		this.cs = cs;
	}
	
	public IPMIClient(String host, String user, String password, CipherSuite cs, Platform platform){
		this.host = host;
		this.password = password;
		this.user = user;
		this.cs = cs;
		IPMI_META_COMMAND = platform.getIPMI_META_COMMAND();
	}

	public boolean verify(){
		ChassisStatusRequest request = new ChassisStatusRequest();
		ChassisStatusRespond chassis = request.sendTo(this);
		return chassis.isChassisStatusOk();
	}


	public String getIPMI_META_COMMAND() {
		return IPMI_META_COMMAND;
	}
}
