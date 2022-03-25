package pack;

public class L3DDataType extends AbstractPTID{
	private CustomFile CF;
	
	
	public L3DDataType(String L3DPATH) 
	{
		CF = new CustomFile(L3DPATH);	
	}
	
	public String GetL3DPATH() 
	{
		return CF.FullPath();
	}
	
	public String GetL3DName() 
	{
		return CF.FileName();
	}
	
   	public Boolean IsL3D() 
   	{ 
   		return CF.IsExtension("l3d");
   	}
   	public Boolean IsXML() 
   	{ 
   		return CF.IsExtension("xml");
   	}
	
	// gets the PTID 
	// this is issued on-site at the hospital
	// it is assumed to be a UID and that it will be used for every 
	// study done at that hospital
	public String GetPTID() 
	{
		return L3DDataType.GetPTID(GetL3DName());
	}
	
	public static String GetPTID( String L3DName) 
	{
		return L3DName.substring(0,L3DName.length()-25);
	}
	
	public String GetDate() 
	{
		String str = GetL3DName().substring(GetL3DName().length()-24,GetL3DName().length()-16);
		String ret = str.substring(0,4) + "-";
		 ret += str.substring(4,6) + "-";
		 ret += str.substring(6,8);
		return ret;
	}
	
	public String GetTime() 
	{
		String str = GetL3DName().substring(GetL3DName().length()-15,GetL3DName().length()-9);
		String ret = str.substring(0,2) + "-";
		 ret += str.substring(2,4) + "-";
		 ret += str.substring(4,6);
		return ret;
	}
}
