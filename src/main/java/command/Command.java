package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {
	public OutputResult exeCmd(String commandStr) {
		BufferedReader br = null;
		OutputResult out = new OutputResult();
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				out.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (br != null)
			{
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return out;
	}

	public static void main(String[] args) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec("ipmiutil-2.9.6-win64/ipmiutil fru -N 10.4.33.146 -U Administrator -P rdis2fun! -J 3");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		System.out.println(sb.toString());
	}
}

