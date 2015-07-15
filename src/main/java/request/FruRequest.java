package request;

import client.IPMIClient;
import command.Command;
import command.OutputResult;
import model.ComponentFru;
import model.Fru;
import model.MainboardFru;
import model.OtherFru;
import respond.FruRespond;
import utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FruRequest extends AbstractRequest {
    private Command command = new Command();

    public void setCommand(Command command) {
        this.command = command;
    }

    private static final Pattern
            fruTypeP = Pattern.compile("^(Component|Mainboard) FRU Size +: +(\\d*)$"),
            pmfP = Pattern.compile("^Product Manufacturer: +(.*)$"),
            pNameP = Pattern.compile("^Product Name : (.*)$"),
            ppNumP = Pattern.compile("^Product Part Number : (.*)$"),
            pVerP = Pattern.compile("^Product Version     : (.*)$"),
            pSerNumP = Pattern.compile("^Product Serial Num  : (.*)$"),
            pAssTagP = Pattern.compile("^Product Asset Tag   : *(.*)$"),
            pFRUFileIDP = Pattern.compile("^Product FRU File ID : *(.*)$"),
            cTypeP = Pattern.compile("^Chassis Type        : (.*)$"),
            cPartNumP = Pattern.compile("^Chassis Part Number : (.*)$"),
            cSerNumP = Pattern.compile("^Chassis Serial Num  : *(.*)$"),
            cOEMFieldP = Pattern.compile("^Chassis OEM Field   : (.*)$"),
            bMfgTimeP = Pattern.compile("^Board Mfg DateTime  : (.*)$"),
            bMfP = Pattern.compile("^Board Manufacturer  : (.*)$"),
            bPNameP = Pattern.compile("^Board Product Name  : (.*)$"),
            bSerNumP = Pattern.compile("^Board Serial Number : (.*)$"),
            bPartNumP = Pattern.compile("^Board Part Number   : (.*)$"),
            bFRUFileIDP = Pattern.compile("Board FRU File ID   : (.*)"),
            bOEMFileIDP = Pattern.compile("^Board OEM Field     : *(.*)$"),
            sysGUIDP = Pattern.compile("^System GUID         : (.*)$"),
            biosVerP = Pattern.compile("^BIOS Version        : (.*)$"),
            endP = Pattern.compile("fruconfig, completed successfully");

    @Override
    public FruRespond sendTo(IPMIClient client) {
        OutputResult or;
        FruRespond fr = new FruRespond();
        String order = buildCommand(client);
        or = command.exeCmd(order);
        if (or != null && or.isNotEmpty()) {
            Fru fru = null;
            String name = "";
            for (String line : or) {
                if (StringUtils.isNotEmpty(line)) {
                    Matcher matcher = fruTypeP.matcher(line);
                    if (matcher.find()) {
                        if (matcher.group(1).matches("Component")) {
                            fru = new ComponentFru(name);
                        } else if (matcher.group(1).matches("Mainboard")) {
                            fru = new MainboardFru(name);
                        } else {
                            fru = new OtherFru(name);
                        }
                        fru.setFruSize(Integer.parseInt(matcher.group(2)));
                        fr.addFru(fru);
                        continue;
                    }
                    if (fru != null) {
                        matcher = pmfP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductManufacturer(matcher.group(1));
                            continue;
                        }
                        matcher = pNameP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductName(matcher.group(1));
                            continue;
                        }
                        matcher = ppNumP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductPartNumber(matcher.group(1));
                            continue;
                        }
                        matcher = pVerP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductVersion(matcher.group(1));
                            continue;
                        }
                        matcher = pSerNumP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductSerialNum(matcher.group(1));
                            continue;
                        }
                        matcher = pAssTagP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductAssetTag(matcher.group(1));
                            continue;
                        }
                        matcher = pFRUFileIDP.matcher(line);
                        if (matcher.find()) {
                            fru.setProductFRUFileID(matcher.group(1));
                            continue;
                        }
                        matcher = cTypeP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setChassisType(matcher.group(1));
                            continue;
                        }
                        matcher = cPartNumP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setChassisPartNumber(matcher.group(1));
                            continue;
                        }
                        matcher = cSerNumP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setChassisSerialNum(matcher.group(1));
                            continue;
                        }
                        matcher = cOEMFieldP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setChassisOEMField(matcher.group(1));
                            continue;
                        }
                        matcher = bMfgTimeP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardMfgDateTime(matcher.group(1));
                            continue;
                        }
                        matcher = bMfP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardManufacturer(matcher.group(1));
                            continue;
                        }
                        matcher = bPNameP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardProductName(matcher.group(1));
                            continue;
                        }
                        matcher = bSerNumP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardSerialNumber(matcher.group(1));
                            continue;
                        }
                        matcher = bPartNumP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardPartNumber(matcher.group(1));
                            continue;
                        }
                        matcher = bFRUFileIDP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardFRUFileID(matcher.group(1));
                            continue;
                        }
                        matcher = bOEMFileIDP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBoardOEMField(matcher.group(1));
                            continue;
                        }
                        matcher = sysGUIDP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setSystemGUID(matcher.group(1));
                            continue;
                        }
                        matcher = biosVerP.matcher(line);
                        if (matcher.find()) {
                            ((MainboardFru) fru).setBiosVersion(matcher.group(1));
                            continue;
                        }
                        matcher = endP.matcher(line);
                        if (matcher.find()) {
                            fr.setSuccess(true);
                            break;
                        }
                    }
                    name = line;
                }
            }
        }
        return fr;
    }

    @Override
    public String getCommandString() {
        String commandString = "fru";
        return commandString;
    }
}
