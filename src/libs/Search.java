package libs;

import java.util.List;

public class Search {

    private Search(){}

	public static boolean InList(int value, List<Integer> list)
    {
		boolean result = false;
		if(!list.isEmpty()){
			try {
                int size =  list.size();
				for(int i = 0; i < size; ++i)
					if(value == list.get(i))
						result = true;
			} catch (Exception ex) {
				return false;
			}
		}
		return result;
	}

	public static boolean InList(String value, List<String> list){
		boolean result = false;
		if(!list.isEmpty())
			try {
                int size =  list.size();
                for(int i = 0; i < size; ++i)
					if(value == list.get(i).toString())
						result = true;
			} catch (Exception ex){
				return false;
			}
	  return result;
	}
	public static boolean InList(double value, List<Double> list)
    {
		boolean result = false;
		if(!list.isEmpty())
			try {
                int size =  list.size();
				for(int i = 0; i < size; ++i)
					if(value == Double.parseDouble(list.get(i).toString()))
						result = true;
			} catch (Exception ex){
				return false;
			}
	  return result;
	}
	
	public static boolean InArray(int value, int[] arrayInt)
    {
		boolean result = false;
		try{
			for(int i = 0; i < arrayInt.length;++i)
				if(value == arrayInt[i])
					result = true;
		} catch (Exception ex){
			return false;
		}
	  return result;
	}

	public static boolean InArray(String value,String[] arrayStr)
    {
		boolean result = false;
		try{
			for(int i = 0; i < arrayStr.length;++i)
				if(value.equals(arrayStr[i]))
					result = true;
		} catch (Exception ex){
			return false;
		}
	  return result;
	}

	public static boolean InArray(double value, double[] arrayDouble)
    {
		boolean result = false;
		try{
			for(int i = 0; i < arrayDouble.length;++i)
				if(value == arrayDouble[i])
					result = true;
		} catch (Exception ex){
			return false;
		}
	  return result;
	}

	public static boolean InArray(char value, char[] arrayChar)
    {
		boolean result = false;
		try{
			for(int i = 0; i < arrayChar.length;++i)
				if(value == arrayChar[i])
					result = true;
		} catch (Exception ex){
			return false;
		}
	  return result;
	}

	public static boolean Are(String refun, String fun){
		boolean result = false;
		if(refun.length() == fun.length())
        {
			char[] val1 = refun.toCharArray();
			char[] val2 = fun.toCharArray();
			int count = refun.length();
			int k = 0;
			for(int i = 0; i < count; ++i)
				if((int)val1[i] == (int)val2[i])
					++k;
			if(k == count)
				result = true;
		}
	  return result;
	}
	
	public static boolean isInteger(String val)
    {
		char[] strChar =  val.trim().toCharArray();
		int count = strChar.length;
		for(int i = 0; i < count; ++i)
			if(((int)strChar[i] < 48 || (int)strChar[i] > 57) && (int)strChar[i] != 46)
				return false;
		return true;
	}
}
