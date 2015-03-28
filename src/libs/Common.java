package libs;

import java.util.ArrayList;
import java.util.List;

public class Common {

	private Common() {}
	
	public static Integer countInfexOf(String separator, String string) {
		Integer result = 0;
		if(!separator.isEmpty()) {
			while(string.indexOf(separator) != -1) {
				++result;
				string = string.replace(separator, "");
			}
		}
		return result;
	}
	
	public static List<String> explode(String separator, String string) {
		List<String> result = new ArrayList<String>();
		Integer count = Common.countInfexOf(separator, string);
		String tmp = "";
		
		for(int i = 0; i <= count; ++i) {
			if(i == count) {
				result.add(string);
				string = "";
			} else {
				tmp = string.substring(0, string.indexOf(separator));
				result.add(tmp);
				string = string.substring(string.indexOf(separator) + 1);
			}
		}
		return result;
	}
	
	public static List<String> explode(String separator, String string, boolean withEmpty) {
		List<String> result = new ArrayList<String>();
		Integer count = Common.countInfexOf(separator, string);
		String tmp = "";
		
		for(int i = 0; i <= count; ++i) {
			if(i == count) {
				if(!string.isEmpty() || withEmpty) {
					result.add(string);
				}
				string = "";
			} else {
				tmp = string.substring(0, string.indexOf(separator));
				if(!tmp.isEmpty() || withEmpty) {
					result.add(tmp);
				}
				
				string = string.substring(string.indexOf(separator) + 1);
			}
		}
		return result;
	}
}
