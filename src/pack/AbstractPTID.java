package pack;

abstract class AbstractPTID {
	
	public abstract String GetPTID();
	
	public int GetPTIDNum() 
	{
		return  Integer.parseInt(GetPTID());
	};
	
	public abstract Boolean IsL3D();
	public abstract Boolean IsXML();

}
