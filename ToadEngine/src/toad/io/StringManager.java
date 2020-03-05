package toad.io;

import java.util.ArrayList;

public class StringManager{
	public String s;
	public ArrayList<String> arr;
	public boolean deactivate;
	public StringManager(){

	}
	
	
	
	public int StringChecker(String s, ArrayList<String> arr){
		if (s.contains("EXIT")) {
			s = s.replaceAll("EXIT", "");
			arr.set(0, s);
			return -1;
		} else if (s.contains("CONTINUE")) {
			s = s.replaceAll("CONTINUE", "");
			arr.set(0, s);
			return 1;
		}else if(s.contains("FINAL")){
			s = s.replaceAll("FINAL", "");
			arr.set(0, s);
			return 2;
		}else {
			arr.set(0, s);
			return 0;
		}
	}
	
	public boolean getDeactivate(){
		return deactivate;
	}
	
	

}
