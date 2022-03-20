package pack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Sorting {
	// Generic auto 
	public <T> ArrayList<T> RunGenericSort(ArrayList<T> list)
	{
		return SortByPTID(list);
	}
	
	public <T> ArrayList<T> RunGenericSortReturnXMLGenericIn(ArrayList<T> list)
	{
		// allows us to make a dynamic conditional and then pass it to a function
		Function <AbstractPTID, Boolean> func = e -> e.IsXML() == true;
		return RunGenericSortReturnXML(WhiteList(list,func));
	}
	
	public <T> ArrayList<T> RunGenericSortReturnL3DGenericIn(ArrayList<T> list)
	{
		// allows us to make a dynamic conditional and then pass it to a function
		Function <AbstractPTID, Boolean> func = e -> e.IsL3D() == true;
		return RunGenericSortReturnL3D(WhiteList(list,func));
	}
	// Generic auto 
		
	
	public <T> ArrayList<T> RunGenericSortReturnXML(ArrayList<T> list)
	{
		return SortByPTID(list);
	}
	
	public <T> ArrayList<T> RunGenericSortReturnL3D(ArrayList<T> list)
	{
		return SortByPTID(list);
	}
	
	public ArrayList<XMLDataType> SortXML(ArrayList<XMLDataType> data)
	{
		return RunGenericSortRemoveDuplicates(data);
	}
	
	public ArrayList<L3DDataType> SortL3D(ArrayList<L3DDataType> data)
	{
		return RunGenericSortRemoveDuplicates(data);
	}
	
	// Run the sort 
	private <T> ArrayList<T> RunGenericSortRemoveDuplicates(ArrayList<T> list)
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
    
    interface Callable {
        public void call(int param);
    }
    
    
    // returns an ArrayList based on a dynamic conditional 
    private <T> ArrayList<T> WhiteList(ArrayList<T> list, Function <AbstractPTID,Boolean> func)
    {
        ArrayList<T> newList = new ArrayList<T>();
        
        // Iterate over the array
        for (T element : list) {
  
        	// run the check
            if (func.apply((AbstractPTID) element))
            {            	            	
                newList.add(element);
            }
        }
        
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


