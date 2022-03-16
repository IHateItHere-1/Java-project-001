package pack;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileFinder {
	
	private String dir;
	private String[] fileList;
	
	public FileFinder (String str)
	{
		RefresData(str) ;
	}
	
	public void RefresData() 
	{
		File file = new File(dir);
		fileList = file.list();
	}
	
	public void RefresData(String str) 
	{
		dir = str;
		File file = new File(str);
		fileList = file.list();
		listf(str);
	}
	
	public String[] fileList() 
	{
		return fileList;
	}
	
	public String[] fileListXML() 
	{
		return fileList;
	}
	
	public String[] fileListL3D() 
	{
		return fileList;
	}
	
	public void Print() 
	{
	     for(String file : fileList) {
	           System.out.println(file);
	         
	       }
	}
	
   public static List<CustomFile> listf(String directoryName) {
        File directory = new File(directoryName);

        List<CustomFile> ret = new ArrayList<CustomFile>();

        // get all the files from a dir
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getAbsolutePath().contains(".")) {
            	CustomFile CF = new CustomFile(file)
            	
            	if(CF.IsXMLOrL3d()) 
            	{
            		ret.add(CF);
            		System.out.println(CF.FullPath());
            	}
                
            } 
        }
        return ret;
    }
   
   public class CustomFile
   {
	    private File file;
	    
	    public CustomFile(File _file)
	    {
	 	    file = _file;
	    }	   
	   
	   	public Boolean IsExtension(String str) 
	   	{ 
	   		return str.toLowerCase().contains(FullPath().substring
			(
				FullPath().lastIndexOf('.') + 1).toLowerCase()
			);
	   	}
	   	
	   	public String FullPath() 
	   	{ 
	   		return file.getAbsolutePath();
	   	}
	   	
	   	public Boolean IsL3D() 
	   	{ 
	   		return (FullPath().substring(
	   				FullPath().lastIndexOf('.') + 1)).toLowerCase().contains("l3d");
	   	}
	   	public Boolean IsXML() 
	   	{ 
	   		return (FullPath().substring(
	   				FullPath().lastIndexOf('.') + 1)).toLowerCase().contains("xml");
	   	}
	   	public Boolean IsXMLOrL3d() 
	   	{ 
	   		return  (IsL3D() || IsXML());
	   	}
	   
   }
	
	
}
