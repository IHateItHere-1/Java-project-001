package pack;

import java.util.ArrayList;

public class RelationBuilder 
{
	
	public ArrayList<Relations> Build(ArrayList<CustomFile> CF) throws Exception
	{
		CustomFile Previous = null;
		ArrayList<Relations> ret = new ArrayList<Relations>();
	    for(CustomFile file : CF) 
	    { 
	    	if( Previous != null) 
	    	{
	    		if(file.GetPTID().equals(Previous.GetPTID())) 
	    		{
	    			// deals with having a duplicated file
	    			// if both return TT or FF then both must be the same 
	    			// file type so we have a duplicated file 
	    			// (duplicated PTID within 2 XMLs for example)
	    			if(file.IsL3D() != Previous.IsL3D()) 
	    			{
		    			System.out.println("///////////////////	Relation ///////////////////");
		    			System.out.println(Previous.GetPTID() + " | " + Previous.FileExtension());
		    			System.out.println(file.GetPTID() + " | " + file.FileExtension());
		    			System.out.println("///////////////////	Relation ///////////////////");
		    			ret.add(new Relations(file,Previous));
	    			}
	    		}
	    	}
	    	
	    	Previous = file;
	    }
	    
	    return ret;
	}
	
}
