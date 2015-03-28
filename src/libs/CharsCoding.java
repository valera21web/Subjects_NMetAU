package libs;

public class CharsCoding {
	public CharsCoding() {}
	
	private int start_value = 1000;
	private int end_value = start_value + 225;
	private String template = "/r";

	
	/**
	 * шифровка
	 * @param str - start string what coding
	 * @return
	 */
	public String encoding(String str) {
		String result = "";
		String newString;
		try {
			newString = new String(str.getBytes("UTF-8"), "UTF-8");
			for(int i = 0; i < newString.length(); ++i) {
				int char_int = (int)newString.charAt(i);
				char char_str = newString.charAt(i);
				String tmp = "";
				if(char_int >= start_value && char_int <= end_value) {
					tmp = Integer.toString((char_int - this.start_value), 16);
					tmp = tmp.length() == 1 ? "0"+tmp : tmp;
					tmp = tmp.toUpperCase();
					result += this.template + tmp;
				} else {
					result += char_str;
				}
				
			}
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * расшифровка
	 * @param str - string from return real text
	 * @return
	 */
	public String decoding(String str) {
		String tmp_str = "";
		Integer index = 0;
		long tmp_int = 0;
		
		
		while((index = str.indexOf(this.template)) != -1)
        {
			tmp_str = str.substring((index+2), (index + 4));
			tmp_int = HexToDec(tmp_str) + this.start_value;
			tmp_str = String.valueOf((char)tmp_int);
			str = str.replace(str.substring((index), (index + 4)), tmp_str);
		}
		
		return str;
	}
	
	public static int pow(int value, int powerNum)
    {
		return (int) Math.pow(value, powerNum);
	}
	
	public static long HexToDec(String hdN)
    {
		long decNum = 0;

		for(int i = 0; i < hdN.length(); i++){
			int powerNum = hdN.length() - i - 1;
			int de= hdN.charAt(i);
			switch(de)
            {
				case '0': decNum += 0 ; break;
				case '1': decNum += pow(16, powerNum); break;
				case '2': decNum += 2 * pow(16, powerNum); break;
				case '3': decNum += 3 * pow(16, powerNum); break;
				case '4': decNum += 4 * pow(16, powerNum); break;
				case '5': decNum += 5 * pow(16, powerNum); break;
				case '6': decNum += 6 * pow(16, powerNum); break;
				case '7': decNum += 7 * pow(16, powerNum); break;
				case '8': decNum += 8 * pow(16, powerNum); break;
				case '9': decNum += 9 * pow(16, powerNum); break;
				case 'A': decNum += 10 * pow(16, powerNum); break;
				case 'a': decNum += 10 * pow(16, powerNum); break;
				case 'B': decNum += 11 * pow(16, powerNum); break;
				case 'b': decNum += 11 * pow(16, powerNum); break;
				case 'C': decNum += 12 * pow(16, powerNum); break;
				case 'c': decNum += 12 * pow(16, powerNum); break;
				case 'D': decNum += 13 * pow(16, powerNum); break;
				case 'd': decNum += 13 * pow(16, powerNum); break;
				case 'E': decNum += 14 * pow(16, powerNum); break;
				case 'e': decNum += 14 * pow(16, powerNum); break;
				case 'F': decNum += 15 * pow(16, powerNum); break;
				case 'f': decNum += 15 * pow(16, powerNum); break;
			}
		}
		return decNum;	
	}

}