package request;

import client.LocalIPMIClient;
import command.Command;
import command.OutputResult;
import respond.FruRespond;
import client.IPMIClient;

public class FruRequest implements IPMIRequest {
	private String commandString = "fru";
	private Command command = new Command();
	@Override
	public FruRespond sendTo(IPMIClient client) {
		OutputResult or;
		FruRespond fr = new FruRespond();
		StringBuilder sb = new StringBuilder(client.getIPMI_META_COMMAND()).append(" ");
		sb.append(commandString).append(" -N ").append(client.getHost()).append(" -U ").append(client.getUser()).append(" -P ").append(client.getPassword()).append(" -J ").append(client.getCs().getId());
		System.out.println(sb);
		if(client instanceof LocalIPMIClient){
			or = command.exeCmd("ipmiutil fru");
		}else{
			or = command.exeCmd(sb.toString());
		}
		if(or!=null && or.isEmpty()){
			System.out.println(or);
		}
		return fr;
	}

}
