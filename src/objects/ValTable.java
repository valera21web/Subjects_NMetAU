package objects;

public class ValTable
{
	private ValRow tHead;
	private ValRows tBody;

    public ValTable(ValRow _tHead, ValRows _tBody)
    {
        this.tHead = _tHead;
        this.tBody = _tBody;
    }

    public ValTable(ValRow _tHead)
    {
        this(_tHead, new ValRows());
    }

    public ValTable(ValRows _tBody)
    {
        this(new ValRow(), _tBody);
    }

    public ValTable()
    {
        this(new ValRow(), new ValRows());
    }
	
	public void setHead(ValRow pHead)
    {
		this.tHead = pHead;
	}
	
	public void setBody(ValRows pBody)
    {
        this.tBody = pBody;
	}

	public String getColumnName(int column)
    {
		if(column < this.tHead.size())
        {
			return this.tHead.get(column);
		} else {
			return null;
		}
	}
	
	public ValRow getRow(int row)
    {
		if(row < this.tBody.size())
            return this.tBody.get(row);
        else
			return null;
	}
	
	public String getCell(int row, int column)
    {
		if(row < this.tBody.size())
        {
			ValRow r = this.tBody.get(row);
			if(column < r.size())
                return r.get(column);
			else
				return null;
		} else
			return null;
	}
	
	public String[] toArrayHead()
    {
		return this.tHead.toArray();
	}
	
	public String[][] toArrayBody()
    {
        return this.tBody.toArray();
	}
}
