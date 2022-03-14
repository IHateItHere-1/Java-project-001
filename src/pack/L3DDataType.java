package pack;

public class L3DDataType extends AbstractPTID{
	private String L3DPATH;
	private String L3DName;
	
	public L3DDataType(String L3DPATH, String L3DName) 
	{
		this.L3DPATH = L3DPATH;
		this.L3DName = L3DName;
		this.L3DName = "6942069_20200724_155800_0000.l3d";
	}
	
	public String GetL3DPATH() 
	{
		return this.L3DPATH;
	}
	
	public String GetL3DName() 
	{
		return this.L3DName;
	}
	
	// gets the PTID 
	// this is issued on-site at the hospital
	// it is assumed to be a UID and that it will be used for every 
	// study done at that hospital
	public String GetPTID() 
	{
		return this.L3DName.substring(0,this.L3DName.length()-25);
	}
}
