package pack;

import java.io.IOException;

public class Relations {
	private CustomFile XML;
	private CustomFile L3D;
	
	public Relations(CustomFile F1, CustomFile F2) throws Exception 
	{
		if(F1.IsL3D() && F2.IsXML()) 
		{
			this.L3D = F1;
			this.XML = F2;			
		}
		else if(F1.IsXML() && F2.IsL3D())
		{
			this.XML = F1;
			this.L3D = F2;		
		}
		else 
		{
			throw new Exception("Relations:Relations() died -> bad file type | F1.IsXML() -> "
					+ F1.IsXML() + " | F2.IsXML() -> " +  F2.IsXML());
		}
	}
	
	public XMLDataType GetXML() 
	{
		return new XMLDataType(XML.FullPath());
	}
	
	public L3DDataType GetL3D() 
	{
		return new L3DDataType(L3D.FullPath());
	}
	
	public boolean CheckDistinctInDB(DB db) 
	{		
		if(!db.IsInDBGeneric(XML) && !db.IsInDBGeneric(L3D)) 
		{
			return true;
		}		
		return false;
	}
	
	public void SaveToDB(DB db) throws IOException 
	{
		db.WriteRelationsToDB(this);
	}
	
	
	
}
