package request;

import client.IPMIClient;
import client.LocalIPMIClient;
import command.Command;
import command.OutputResult;
import respond.ChassisStatusRespond;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChassisStatusRequest implements IPMIRequest {
    private Command command = new Command();

    @Override
    public ChassisStatusRespond sendTo(IPMIClient client) {
        OutputResult or;
        ChassisStatusRespond csr = new ChassisStatusRespond();
        if (client instanceof LocalIPMIClient) {
            or = command.exeCmd(client.getIPMI_META_COMMAND() + " ipmiutil health");
        } else {
            or = command.exeCmd(String.format("%s health -N %s -U 5s -P %s -J %s", client.getIPMI_META_COMMAND(), client.getHost(), client.getUser(), client.getPassword(), client.getCs().getId()));
        }
        if (or.isNotEmpty()) {
            Pattern selfTest = Pattern.compile("Selftest status += \\d+ +(\\(.*\\)).*");
            Pattern version = Pattern.compile("BMC version +=.+, IPMI v(\\d*\\.\\d*).*");
            Pattern error = Pattern.compile(".*(ipmiutil health, error -1).*");
            for (String line : or) {
                Matcher matcher = error.matcher(line);
                if (matcher.find()) {
                    csr.setChassisStatusOk(false);
                    break;
                }
                matcher = selfTest.matcher(line);
                if (matcher.find()) {
                    csr.setSelftest(matcher.group(1));
                    csr.setChassisStatusOk(true);
                    continue;
                }
                matcher = version.matcher(line);
                if (matcher.find()) {
                    csr.setVersion(matcher.group(1));
                    csr.setChassisStatusOk(true);
                }
            }
        } else {
            csr.setChassisStatusOk(false);
        }
        return csr;
    }
}
