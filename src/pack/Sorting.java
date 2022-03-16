package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorting {
	public ArrayList<XMLDataType> SortXML(ArrayList<XMLDataType> data)
	{
		return RunGenericSort(data);
	}
	
	public ArrayList<L3DDataType> SortL3D(ArrayList<L3DDataType> data)
	{
		return RunGenericSort(data);
	}
	
	// Run the sort 
	private <T> ArrayList<T> RunGenericSort(ArrayList<T> list)
	{
		return SortByPTID(removeDuplicates(list));
	}
	
	// gets rid of duplicated data
    private <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();
  
        // Iterate over the array
        for (T element : list) {
  
        	// don't add duplicates
            if (!newList.contains(element)) 
            {            	            	
                newList.add(element);
            }
        }
  
        // return the new list
        return newList;
    }
    
    // works on the basis of the abstract known PTID method 
    private <T> ArrayList<T> SortByPTID(ArrayList<T> list)
    {
    	
    	Collections.sort(list, new Comparator<T>()
        {
    		// override the comparator with a generic
            @Override
            public int compare(T o1, T o2)
            {
               return ((AbstractPTID)o1).GetPTIDNum() - ((AbstractPTID)o2).GetPTIDNum();
            }
        });
    	
        return list;
    }
}


