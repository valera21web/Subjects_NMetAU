package libs;

import java.io.UnsupportedEncodingException;

public class Cript
{
	private Cript(){}
	
	static public String cache(String text, String key) {
		if(text.trim().length() == 0 || key.trim().length() == 0)
			return "";
		boolean SubCall = false;
		String result = "", tmp = "", admKey = "", key_cash = "";
		byte[] source;
		int[] strInt = new int[8];
		int minLength = 8, index = 0, i, j;
		
		String adm_key_system = "♫☺♫♥☻♫○♫", adm_key = "AedM0inKe1yS1";
		if(key == adm_key_system)
			SubCall = true;
		if(!SubCall) {
			admKey = Cript.cache(adm_key, adm_key_system);
			key_cash = Cript.cache(key, adm_key_system);
		}
		try {
			source = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			source = text.getBytes();
		}
		for(i = 0; i < source.length; ++i)
			tmp += Integer.toString((int)Math.pow(source[i], i + 1));
		int countStr = tmp.length();

		for(i = 0; i < countStr-1; ++i) {
			index = (int) (i < minLength ? i : i % minLength);
			strInt[index] += Integer.parseInt(tmp.substring(i, i+1));
		}
		if(!SubCall) {
			// Next step with user key
			try {
				source = key_cash.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				source = key_cash.getBytes();
			}
			tmp = "";
			for(i = 0; i < source.length; ++i)
				tmp += source[i];
			countStr = tmp.length();
			for(i = 0; i < countStr-1; ++i) {
				index = (++index < minLength) ? index++ : 0 ;
				strInt[index] += Integer.parseInt(tmp.substring(i, i+1));
			}
			// Next step with admin key
			try {
				source = admKey.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				source = admKey.getBytes();
			}
			tmp = "";
			for(i = 0; i < source.length; ++i)
				tmp += source[i];
			countStr = tmp.length();
			for(i = 0; i < countStr-1; ++i) {
				index = (++index < minLength) ? index++ : 0 ;
				strInt[index] += Integer.parseInt(tmp.substring(i, i+1));
			}
		}
		
		for(i = 0; i < minLength; ++i)
			result += Integer.toHexString(strInt[i]);
		
		if(!SubCall) {
			index = 0;
			while(result.length() < 32) {
				if(index == 0) {
					tmp = key_cash;
				} else if(index == 1) {
					tmp = admKey;
				} else {
					tmp = Cript.cache(key_cash+admKey, adm_key_system);
				}

				j = 0;
				for(i = 1; i < result.length() - 2 && j < tmp.length() && result.length() < 32; i += j) {
					result = result.substring(0, i) + tmp.substring(j, j + 1) +result.substring(i, result.length());
					++j;
				}
				++index;
			}
		}
		return result.trim();
	}
	
}
