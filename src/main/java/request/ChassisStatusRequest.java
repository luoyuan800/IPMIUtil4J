package request;

import client.IPMIClient;
import command.Command;
import command.OutputResult;
import respond.ChassisStatusRespond;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChassisStatusRequest extends AbstractRequest {
    private Command command = new Command();
    private static final Pattern
            biosVerP = Pattern.compile("BIOS Version     = (.*)"),
            chassisStatusP = Pattern.compile("Chassis Status   = (\\d+) *\\((.*), restore_policy=(.*)\\)"),
            powerStateP = Pattern.compile("Power State      = (\\d+) *\\((.*)\\)"),
            selfTestP = Pattern.compile("Selftest status += \\d+ +\\((.*)\\).*"),
            versionP = Pattern.compile("BMC version +=.+, IPMI v(\\d*\\.\\d*).*"),
            endP = Pattern.compile("bmchealth, completed successfully"),
            errorP = Pattern.compile(".*(ipmiutil health, error -1).*");

    @Override
    public ChassisStatusRespond sendTo(IPMIClient client) {
        OutputResult or;
        ChassisStatusRespond csr = new ChassisStatusRespond();
        or = command.exeCmd(buildCommand(client));
        if (or.isNotEmpty()) {
            for (String line : or) {
                Matcher matcher = errorP.matcher(line);
                if (matcher.find()) {
                    csr.setSuccess(false);
                    break;
                }
                matcher = selfTestP.matcher(line);
                if (matcher.find()) {
                    csr.setSelftest(matcher.group(1));
                    continue;
                }
                matcher = chassisStatusP.matcher(line);
                if (matcher.find()) {
                    csr.setPowerRestorePolicy(matcher.group(3));
                    csr.setChassisStatusOk(matcher.group(2).matches("on"));
                    continue;
                }
                matcher = powerStateP.matcher(line);
                if (matcher.find()) {
                    csr.setPowerOn(matcher.group(2).contains("working"));
                    continue;
                }
                matcher = endP.matcher(line);
                if (matcher.find()) {
                    csr.setSuccess(true);
                    break;
                }
                matcher = versionP.matcher(line);
                if (matcher.find()) {
                    csr.setVersion(matcher.group(1));
                }
            }
        } else {
            csr.setSuccess(false);
        }
        return csr;
    }

    @Override
    public String getCommandString() {
        return "health";
    }

    public void setCommand(Command command){
        this.command = command;
    }
}
