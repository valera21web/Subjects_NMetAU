package objects;

import java.util.ArrayList;
import java.util.List;

public class ValRow
{
	private List<String> rData;

    public ValRow(List<String> _rData)
    {
        this.rData = _rData;
    }

    public ValRow(String[] _rData)
    {
        this.rData = new ArrayList<String>();
        for(String row: _rData)
        {
            this.set(row);
        }
    }

    public ValRow(Integer[] _rData)
    {
        this.rData = new ArrayList<String>();
        for(Integer row: _rData)
        {
            this.set(row);
        }
    }

    public ValRow(Double[] _rData)
    {
        this.rData = new ArrayList<String>();
        for(Double row: _rData)
        {
            this.set(row);
        }
    }

    public ValRow(Object[] _rData)
    {
        this.rData = new ArrayList<String>();
        for(Object row: _rData)
        {
            this.set(row);
        }
    }

    public ValRow()
    {
        this(new ArrayList<String>());
    }

	public String get(int index)
    {
		if(index < this.rData.size())
			return this.rData.get(index);
		else
			return null;
	}
	
	public void set(String value)
    {
        this.rData.add(value);
	}
	
	public void set(Integer value)
    {
        this.rData.add(""+value);
	}
	
	public void set(Double value)
    {
        this.rData.add(""+value);
	}

    public void set(Object value)
    {
        this.rData.add(""+value);
    }
	
	public int size()
    {
		return this.rData.size();
	}
	
	public String[] toArray()
    {
		String[] array = new String[this.rData.size()];
		int i = 0;
		for(String str : this.rData) {
			array[i] = str;
			++i;
		}
		return array;
	}
	
	public String toString() {
		String res = "{";
		int count = this.rData.size();
		int i = 0;
		for(String str : this.rData) {
			res += str + ( i < count - 1 ? ", " : "");
			++i;
		}
		res += "}";
		return res;
	}
	
}
