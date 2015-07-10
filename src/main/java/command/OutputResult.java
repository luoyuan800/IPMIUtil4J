package command;

import java.util.LinkedList;

public class OutputResult extends LinkedList<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();
		for(String s : this){
			sb.append(s).append("\n");
		}
		return sb.toString();
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}
}
