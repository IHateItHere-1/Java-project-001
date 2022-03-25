package pack;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DriverCode {
	static String Path;
	public static void main(String[] args) throws Exception 
    {
    	Path currentRelativePath = Paths.get("");
    	Path = currentRelativePath.toAbsolutePath().toString();
    	
    	FileFinder _file = new FileFinder(Path);  	
    	RelationBuilder RB = new RelationBuilder();
    	ArrayList<Relations> data = RB.Build(_file.fileList());
    	DB db = new DB();
    	 for(Relations file : data) 
    	 {
    		 System.out.println("///////////////////	XML ///////////////////");
    		 System.out.println(file.GetXML().GetXmlDBData().MRN);
    		 System.out.println(file.GetXML().GetXmlDBData().FIRST);
    		 System.out.println(file.GetXML().GetXmlDBData().MIDDLE);
    		 System.out.println(file.GetXML().GetXmlDBData().LAST);
    		 System.out.println(file.GetXML().GetXmlDBData().DOB);
    		 System.out.println(file.GetXML().GetXmlDBData().SEX);
    		 System.out.println("///////////////////	XML ///////////////////");
    		 file.SaveToDB(db);
    	 }
    	
    	
    	
    	
    	//System.out.println(L3DDataType.GetTime());
    }
}
