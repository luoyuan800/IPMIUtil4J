package respond;

public class ChassisStatusRespond implements IPMIRespond {
	private String selftest;
	private String version;
	private boolean isPowerOn;
	private String powerRestorePolicy;
	private String frontPanelLockout;
	private boolean isDriveFault;
	private boolean isCoolingFanFault;
	private long powerOnHours;
	private boolean isChassisStatusOk;
	private boolean success;
	public String getSelftest() {
		return selftest;
	}
	public void setSelftest(String selftest) {
		this.selftest = selftest;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isPowerOn() {
		return isPowerOn;
	}
	public void setPowerOn(boolean isPowerOn) {
		this.isPowerOn = isPowerOn;
	}
	public String getPowerRestorePolicy() {
		return powerRestorePolicy;
	}
	public void setPowerRestorePolicy(String powerRestorePolicy) {
		this.powerRestorePolicy = powerRestorePolicy;
	}
	public boolean isDriveFault() {
		return isDriveFault;
	}
	public void setDriveFault(boolean isDriveFault) {
		this.isDriveFault = isDriveFault;
	}
	public boolean isCoolingFanFault() {
		return isCoolingFanFault;
	}
	public void setCoolingFanFault(boolean isCoolingFanFault) {
		this.isCoolingFanFault = isCoolingFanFault;
	}
	public long getPowerOnHours() {
		return powerOnHours;
	}
	public void setPowerOnHours(long powerOnHours) {
		this.powerOnHours = powerOnHours;
	}
	public String getFrontPanelLockout() {
		return frontPanelLockout;
	}
	public void setFrontPanelLockout(String frontPanelLockout) {
		this.frontPanelLockout = frontPanelLockout;
	}
	public boolean isChassisStatusOk() {
		return isChassisStatusOk;
	}
	public void setChassisStatusOk(boolean isChassisStatusOk) {
		this.isChassisStatusOk = isChassisStatusOk;
	}
	@Override
	public boolean hasResponsed() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
