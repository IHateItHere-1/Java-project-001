package pack;

public class XMLDataType extends AbstractPTID{
	private CustomFile CF;
	String PTID;

	public XMLDataType(String XMLPATH) 
	{
		CF = new CustomFile(XMLPATH);
		PTID = XML.GetXMLFilePTIDString(XMLPATH);	
	}
	
	public String GetXMLPATH() 
	{
		return this.CF.FullPath();
	}
	
	public String GetPTID() 
	{
		return this.PTID;
	}
	
   	public Boolean IsL3D() 
   	{ 
   		return CF.IsExtension("l3d");
   	}
   	public Boolean IsXML() 
   	{ 
   		return CF.IsExtension("xml");
   	}
	
}
