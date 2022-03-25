package pack;

public class XMLDBDATA {
	
	public String MRN;
	public String FIRST;
	public String MIDDLE;
	public String LAST;
	public String DOB;
	public String SEX;
	
	public XMLDBDATA(XMLDataType data ) 
	{
		String Path = data.GetXMLPATH();
		MRN = data.GetPTID();
		FIRST = XML.GetXMLFileDataDynamic(Path, "first-name");
		MIDDLE = XML.GetXMLFileDataDynamic(Path, "middle-name");
		LAST = XML.GetXMLFileDataDynamic(Path, "last-name");
		DOB = XML.GetXMLFileDataDynamic(Path, "birthday");
		SEX = XML.GetXMLFileDataDynamic(Path, "sex");
	}
	
	
}
