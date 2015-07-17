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

	/**
	 * IPMIClient is representative as the target device which support IPMI
	 * @param host The BMC network address( IPMI card IP)
	 * @param user The IPMI user name config in the BMC
	 * @param password The IPMI password config for the user
	 * @param cs Cipher suite must support when doing initialize
	 * @see param.CipherSuite
	 * @param platform The project you want to run in which platform. It was very important.
	 */
	public IPMIClient(String host, String user, String password, CipherSuite cs, Platform platform){
		this.host = host;
		this.password = password;
		this.user = user;
		this.cs = cs;
		IPMI_META_COMMAND = platform.getIPMI_META_COMMAND();
		this.platform = platform;
	}

	/**
	 * Verify if this IPMI device could reach by IPMI command.
	 * @return true, IPMI is eable
	 * 		   false, IPMI command faile, maybe this device did not support IPMI or the BMC is down.
	 */
	public boolean verify(){
		ChassisStatusRequest request = new ChassisStatusRequest();
		ChassisStatusRespond chassis = request.sendTo(this);
		systemPowerUp = chassis.isPowerOn();
		return chassis.isChassisStatusOk();
	}

	/**
	 * Power up the system install in the target device
	 * @return true, power up complete
	 */
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

	/**
	 * Power down the system in target device chassis
	 * @return If return true, the IPMIClient.powerUp will set as true
	 */
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

	/**
	 * Power down the system in target device chassis
	 * Will try to check if the system already power up,
	 * if not, just power up system,
	 * if yes, will do the restart.
	 * @return If return true, the IPMIClient.powerUp will set as true
	 */
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

	/**
	 * This command will different for different Platform
	 * @see param.Platform
	 * @return The ipmiutil command format for different platforms.
	 */
	public String getIPMI_META_COMMAND() {
		return IPMI_META_COMMAND;
	}

	/**
	 * This is not a real time status of the target device system
	 * If the powerUpSystem or powerCycleSystem() have been called before, this status will set as true
	 * @return
	 * @see #powerUpSystem()
	 * @see #powerCycleSystem()
	 */
	public boolean isSystemPowerUp() {
		return systemPowerUp;
	}

	public Platform getPlatform() {
		return platform;
	}
}
