package client;

import param.CipherSuite;
import param.Platform;
import request.ChassisStatusRequest;
import request.ResetRequest;
import respond.ChassisStatusRespond;

public class IPMIClient {
	private String IPMI_META_COMMAND = "";
	private String user;
	private String password;
	private String host;
	private CipherSuite cs;
	private boolean systemPowerUp;
	private Platform platform;
	public String getHost() {
		return host;
	}
	public String getPassword() {
		return password;
	}
	public String getUser() {
		return user;
	}
	public CipherSuite getCs() {
		return cs;
	}

	public IPMIClient(String host, String user, String password, CipherSuite cs, Platform platform){
		this.host = host;
		this.password = password;
		this.user = user;
		this.cs = cs;
		IPMI_META_COMMAND = platform.getIPMI_META_COMMAND();
		this.platform = platform;
	}

	public boolean verify(){
		ChassisStatusRequest request = new ChassisStatusRequest();
		ChassisStatusRespond chassis = request.sendTo(this);
		systemPowerUp = chassis.isPowerOn();
		return chassis.isChassisStatusOk();
	}

	public boolean powerUpSystem(){
		ResetRequest request = new ResetRequest();
		request.setPowerUpSystem(true);
		if(request.sendTo(this).hasRespond()){
			systemPowerUp = true;
			return true;
		}else{
			return false;
		}
	}

	public boolean powerDownSystem(){
		ResetRequest request = new ResetRequest();
		request.setPowerDownSystem(true);
		if(request.sendTo(this).hasRespond()){
			systemPowerUp = false;
			return true;
		}else{
			return false;
		}
	}

	public boolean powerCycleSystem(){
		if(systemPowerUp) {
			ResetRequest request = new ResetRequest();
			request.setPowerCycleSystem(true);
			return request.sendTo(this).hasRespond();
		}else{
			systemPowerUp = powerUpSystem();
			return systemPowerUp;
		}
	}

	public String getIPMI_META_COMMAND() {
		return IPMI_META_COMMAND;
	}

	public boolean isSystemPowerUp() {
		return systemPowerUp;
	}

	public Platform getPlatform() {
		return platform;
	}
}
