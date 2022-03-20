package pack;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileFinder {
	
	private String dir;
	private String[] fileList;
	private ArrayList<CustomFile> Flist;
	
	public FileFinder(String str)
	{
		RefresData(str);
	}
	
	public void RefresData() 
	{
		RefresData(dir);
	}
	
	public void RefresData(String str) 
	{
		dir = str;
		File file = new File(str);
		fileList = file.list();
		Flist = listf(str);
	}
	
	public ArrayList<CustomFile> fileList() 
	{
		Sorting sort = new Sorting();
		var t = sort.RunGenericSort(Flist);
	    for(var file : t) 
	    {
	    	System.out.println(file.GetPTID() + " | " + file.FileExtension());    
	    }
	    return t;
	}
	
	public void fileListXML() 
	{
		Sorting sort = new Sorting();
		var t = sort.RunGenericSortReturnXMLGenericIn(Flist);
	    for(var file : t) 
	    {
	    	System.out.println(file.GetPTID() + " | " + file.FileExtension());         
	    }
	}
	
	public String[] fileListL3D() 
	{
		return fileList;
	}
	
	public void Print() 
	{
	     for(String file : fileList) 
	     {
	    	 System.out.println(file);	         
         }
	}
	
   public ArrayList<CustomFile> listf(String directoryName) {
        File directory = new File(directoryName);

        ArrayList<CustomFile> ret = new ArrayList<CustomFile>();

        // get all the files from a dir
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getAbsolutePath().contains(".")) {
            	CustomFile CF = new CustomFile(file);
            	
            	if(CF.IsXMLOrL3d()) 
            	{
            		ret.add(CF);
            		System.out.println(CF.FullPath());
            	}
                
            } 
        }
        return ret;
    }

   
   
}
	
	
