package pack;

public class XMLDataType {
	private String XMLPATH;
	private String PTID;
	
	public XMLDataType(String XMLPATH, String PTID) 
	{
		this.XMLPATH = XMLPATH;
		this.PTID = PTID;
	}
	
	public String GetXMLPATH() 
	{return this.XMLPATH;}
	
	public String GetPTID() 
	{return this.PTID;}
}
