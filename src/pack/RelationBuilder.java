package pack;

import java.util.ArrayList;

public class RelationBuilder 
{
	
	public void Build(ArrayList<CustomFile> CF)
	{
		CustomFile Previous = null;
	    for(CustomFile file : CF) 
	    { 
	    	if( Previous != null) 
	    	{
	    		if(file.GetPTID().equals(Previous.GetPTID())) 
	    		{
	    			// check that both are not the same file type
	    			// deals with having a duplicated file
	    			if(file.IsL3D() != Previous.IsL3D()) 
	    			{
		    			System.out.println("///////////////////	Relation ///////////////////");
		    			System.out.println(Previous.GetPTID() + " | " + Previous.FileExtension());
		    			System.out.println(file.GetPTID() + " | " + file.FileExtension());
		    			System.out.println("///////////////////	Relation ///////////////////");
	    			}
	    		}
	    	}
	    	
	    	Previous = file;
	    }
	}
	
}
