package toad.ui;

import java.util.ArrayList;

public class StringManager{
	public String s;
	public ArrayList<String> arr;
	public boolean deactivate;
	public StringManager(){

	}
	
	public StringManager(String s, ArrayList<String> arr, boolean deactivate) {
		this.s = s;
		this.arr = arr;
		this.deactivate = deactivate;
	}
	
	
	public void StringChecker(){
		if (s.contains("EXIT")) {
			s = s.replaceAll("EXIT", "");
			arr.set(0, s);
			deactivate = true;
		} else if (s.contains("RETURN")) {
			s = s.replaceAll("RETURN", "");
			arr.set(0, s);
		} else {
			arr.set(0, s);
		}
	}
	
	public boolean getDeactivate(){
		return deactivate;
	}
	
	

}
