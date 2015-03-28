package objects;

import java.util.ArrayList;
import java.util.List;

public class ValRows
{
	private List<ValRow> tRows;
	private int lenght = 0;

    public ValRows(List<ValRow> _tRows)
    {
        this.tRows = _tRows;
    }

    public ValRows()
    {
        this(new ArrayList<ValRow>());
    }

	public void set(ValRow pRow)
    {
		this.tRows.add(pRow);
        this.setSizeCells(pRow.size());
	}
	
	public ValRow get(int index)
    {
		if(index < this.tRows.size())
			return this.tRows.get(index);
		else
			return null;
	}

	public int getSizeCells()
    {
		return this.lenght;
	}

	private void setSizeCells(int pLenght)
    {
		if(pLenght > this.lenght)
        {
			this.lenght = pLenght;
		}
	}
	
	public int size()
    {
		return this.tRows.size();
	}

    public String[][] toArray()
    {
        String[][] array = new String[this.size()][];
        int i = 0;
        for(ValRow row: this.tRows)
        {
            array[i] = row.toArray();
            ++i;
        }
        return array;
    }
}
