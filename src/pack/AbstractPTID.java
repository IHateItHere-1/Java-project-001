package pack;

abstract class AbstractPTID {
	
	public abstract String GetPTID();
	
	public int GetPTIDNum() 
	{
		return  Integer.parseInt(GetPTID());
	};

}
