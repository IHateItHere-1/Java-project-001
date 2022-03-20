package pack;

import java.io.File;

public class CustomFile extends AbstractPTID
{
	    private File file;
	    
	    public CustomFile(File _file)
	    {
	 	    file = _file;
	    }
	    
	    public CustomFile(String _file)
	    {
	 	    file = new File(_file);
	    }
	    
	    public String FileExtension() 
	    {
	    	return FullPath().substring(FullPath().lastIndexOf('.') + 1).toLowerCase();
	    }
	   
	   	public Boolean IsExtension(String str) 
	   	{ 
	   		return str.toLowerCase().contains(FileExtension());
	   	}
	   	
	   	public String FullPath() 
	   	{ 
	   		return file.getAbsolutePath();
	   	}
	   	
	   	public String FileName() 
	   	{ 
	   		return file.getName();
	   	}	   	
	   	
	   	public Boolean IsL3D() 
	   	{ 
	   		return IsExtension("l3d");
	   	}
	   	public Boolean IsXML() 
	   	{ 
	   		return IsExtension("xml");
	   	}
	   	public Boolean IsXMLOrL3d() 
	   	{ 
	   		return  (IsL3D() || IsXML());
	   	}
	   	
		public String GetPTID() 
		{
			if(IsL3D()) 
			{
				return L3DDataType.GetPTID(FileName());
			}
			else
			{
				return XML.GetXMLFilePTIDString(FullPath());
			}
		}
		
	}// end class CustomFile