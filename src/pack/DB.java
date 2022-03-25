package pack;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class DB {
	private static Connection c = null; 
    public DB() {
    	
		 File f = new File(DriverCode.Path + "//ImportedXML//");
		 f.mkdir();
		 
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:DB.db");
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Opened database successfully");
        
        // don't mind if this goes bang
        try 
        {
        	MakeTables();
        }
        catch(Exception e) {};
             
    }
    
    public void WriteRelationsToDB(Relations data) throws IOException 
    {
    	WriteXMLToDB(data.GetXML());
    	WriteL3DToDB(data.GetL3D());
    }
     
    private void WriteXMLToDBNoCheck(XMLDataType data) throws IOException 
    {
    	String sql = "INSERT INTO data(MRN, FIRST, MIDDLE, LAST, DOB, SEX) VALUES(?,?,?,?,?,?)";
    	XMLDBDATA XMLDATA = data.GetXmlDBData();
        try (PreparedStatement pstmt = c.prepareStatement(sql)) 
        {
        	
            pstmt.setString(1, XMLDATA.MRN);
            pstmt.setString(2, XMLDATA.FIRST);
            pstmt.setString(3, XMLDATA.MIDDLE);
            pstmt.setString(4, XMLDATA.LAST);
            pstmt.setString(5, XMLDATA.DOB);
            pstmt.setString(6, XMLDATA.SEX);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        MoveImportedFilesXml(data.GetXMLPATH(),data.GetXMLName());
        
    }
    
    private void WriteL3DToDBNoCheck(L3DDataType data) throws IOException 
    {
    	String sql = "INSERT INTO files(MRN, DATE, TIME, PATH) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) 
        {
            pstmt.setString(1, data.GetPTID());
            pstmt.setString(2, data.GetDate());
            pstmt.setString(3, data.GetTime());
            pstmt.setString(4, data.GetL3DPATH());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        MoveImportedFilesL3D(data.GetL3DPATH(), data);
    }
    
    public void WriteXMLToDB(XMLDataType data) throws IOException 
    {
    	 if(!ISXMLInDB(data)) 
    	 {
    		 WriteXMLToDBNoCheck(data);
    	 }
    	 else 
    	 {
    		 MoveImportedFilesXml(data.GetXMLPATH(),data.GetXMLName());
    	 }
    }
    
    private void MoveImportedFilesXml(String In, String Out ) throws IOException 
    {

        Files.move(Paths.get(In), Paths.get(DriverCode.Path + "//ImportedXML//" + Out));
    }
   
    private void MoveImportedFilesL3D(String In, L3DDataType data ) throws IOException 
    {
    	String str = data.GetDate();
		String ret = str.substring(0,4) + "//";
		 ret += str.substring(5,7) + "//";
		 ret += str.substring(8,10);
		 System.out.println(DriverCode.Path + "//ImportedL3D//" + ret);
		 new File(DriverCode.Path + "//ImportedL3D//" + ret).mkdirs();
		 
		 ret += "//" + data.GetL3DName();
		 
        Files.move(Paths.get(In), Paths.get(DriverCode.Path + "//ImportedL3D//" + ret));
    }
    
    public void WriteL3DToDB(L3DDataType data) throws IOException 
    {
	   	 if(!ISL3DInDB(data)) 
	   	 {
	   		WriteL3DToDBNoCheck(data);
	   	 }
	   	 else 
	   	 {
	   		MoveImportedFilesL3D(data.GetL3DPATH(), data);
	   	 }
    }
     
    public Boolean ISXMLInDB(XMLDataType data) 
    {
    	return IsInDBGeneric("data", new String[] { "MRN = " + data.GetPTID() });
    }
    
    public Boolean ISL3DInDB(L3DDataType data) 
    {
    	return IsInDBGeneric("files", new String[] { "PATH = " + data.GetL3DPATH() });
    }
    
    public Boolean IsInDBGeneric(CustomFile CF) 
    {
    	if(CF.IsL3D()) 
    	{
    		return ISL3DInDB(new L3DDataType(CF.FullPath()));
    	}
    	else 
    	{
    		return ISXMLInDB(new XMLDataType(CF.FullPath()));
    	}
    }
    
    private Boolean IsInDBGeneric(String Table, String[] params) 
    {
    	try {
			Statement Stat = c.createStatement();
			String sql = "SELECT * FROM " + Table + " WHERE ";
			
			for(String S : params) 
			{
				sql += "'" + S + "'" + " AND ";
			}
			
			sql = sql.substring(0, sql.length()-5);
			
			System.out.println(sql);
			
			ResultSet rs = Stat.executeQuery(sql);
			return rs.getRow() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    
    // generating the Relation in SQL is cool and all 
    // BUT one of the requirements of this software is that it's stand-alone 
    // so no MSSQL server 2019
    // SQLLITE is just a wrapper for a file and is really very slow.
    
    // once you get to a few 100 PTs things get very slow to load in.
    
    // making the relations in ram is much faster
    // this system is meant to be linked with a PACs reporting system that uses TCP
    // so when you open the PT in the PACs system it opens the same study in this software
    
    private void MakeTables() {
        Connection c = null;
        Statement Stat = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:DB.db");
           System.out.println("Opened database successfully");

           Stat = c.createStatement();
           String sql = "CREATE TABLE if not exists data(id INTEGER PRIMARY KEY, MRN TEXT, FIRST TEXT, MIDDLE TEXT, LAST TEXT, DOB TEXT, SEX TEXT)"; 
           Stat.executeUpdate(sql);
           sql = "CREATE TABLE if not exists files(id INTEGER PRIMARY KEY, MRN TEXT, DATE TEXT, TIME TEXT, PATH TEXT)"; 
           Stat.executeUpdate(sql);
           
           Stat.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
     }

}
