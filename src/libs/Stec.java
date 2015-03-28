package libs;

import java.util.ArrayList;
import java.util.List;


public class Stec {
	
	private List<String> data;
	private int length = 0;
	private int key = 0;

	public Stec(int len) {
		data = new ArrayList<String>();
		length = len;
		key = 0;
	}
	
	public void add(String val) {
		if(key < length) {
			data.add(key, val);
			++key;
		} else {
			for(int i = 0; i < length - 1; ++i) {
				data.set(i, data.get(i+1));
			}
			data.set(length - 1, val);
		}
	}
	
	public boolean inStec(String search) {
		try{
			for(int i = 0; i <= key; ++i) {
				if(Search.Are(data.get(i), search)) {
					return true;
				}
			}					
		} catch (Exception ex){
			return false;
		}
		return false;
	}
	
	public String get()
    {
		if(key > 0)
        {
            --key;
			return data.get(key);
		} else
			return "";
	}
	
	public boolean empty() {
        return key == 0;
	}
}
