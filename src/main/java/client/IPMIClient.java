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
	 * IPMIClient 代表目标主机，初始化的时候需要传入BMC的IP地址和在目标BMC上设置的用户名与密码
	 * @param host BMC的IP地址，支持IPMI的主机会有独立的网络接口提供给IPMI连接( IPMI card IP)
	 * @param user 在目标主机上配置的IPMI连接用的用户名
	 * @param password 在目标主机上配置的IPMI连接用的用户名对应的密码
	 * @param cs Cipher suite 是连接的加密方式，IPMI2.0要求提供
	 * @see param.CipherSuite
	 * @param platform 作为IPMI客户端的运行平台需要在这里指定
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
	 * 验证目标主机是否支持IPMI
	 * @return true, IPMI端口开启
	 * 		   false, 不能通过IPMI连接目标主机，可能原因是目标主机不支持IPMI协议或者IPMI模块没有通电或者联网.
	 */
	public boolean verify(){
		ChassisStatusRequest request = new ChassisStatusRequest();
		ChassisStatusRespond chassis = request.sendTo(this);
		systemPowerUp = chassis.isPowerOn();
		return chassis.isChassisStatusOk();
	}

	/**
	 * 发送开机命令到目标主机上
	 * @return true, 开机完成
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
	 * 发送关机命令到目标主机上
	 * @return 如果返回true，表示关机成，并且IPMIClient.powerUp 会被设置成 false
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
	 * 重启目标和主机上安装的操作系统
	 * 首先会检查目标主机状态是否处于开机状态，如果是关机状态的话会调用开机操作，否则的话直接进重启操作
	 * @return 如果操作完成会返回true， 同时会将IPMIClient.powerUp状态设置为 true
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
	 * 这个状态标示目标主机上安装的操作系统状态，但是这个状态不是实时获取的，可能会存在误差
	 * 如果在调用这个方法之前调用了 powerUpSystem() 或者 powerCycleSystem()方法，那么这个方法会返回true
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
