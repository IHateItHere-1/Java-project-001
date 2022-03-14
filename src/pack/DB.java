package pack;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class DB {
	private static Connection c = null; 
    public DB() {

             
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
    
    public Boolean ISXMLInDB(XMLDataType data) 
    {
    	return IsInDBGeneric("data", new String[] { "MRN = " + data.GetPTID() });
    }
    
    public Boolean ISL3DInDB(L3DDataType data) 
    {
    	return IsInDBGeneric("files", new String[] { "PATH = " + data.GetL3DPATH() });
    }
    
    private Boolean IsInDBGeneric(String Table, String[] params) 
    {
    	try {
			Statement Stat = c.createStatement();
			String sql = "SELECT * FROM " + Table + " WHERE ";
			
			for(String S : params) 
			{
				sql += S + " AND ";
			}
			
			sql = sql.substring(0, sql.length()-5);
			ResultSet rs = Stat.executeQuery(sql);
			rs.last();
			return rs.getRow() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    
    private void MakeTables() {
        Connection c = null;
        Statement Stat = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           System.out.println("Opened database successfully");

           Stat = c.createStatement();
           String sql = "CREATE TABLE data(id INTEGER PRIMARY KEY, MRN TEXT, FIRST TEXT, MIDDLE TEXT, LAST TEXT, DOB TEXT, SEX TEXT)"; 
           Stat.executeUpdate(sql);
           sql = "CREATE TABLE files(id INTEGER PRIMARY KEY, MRN TEXT, DATE TEXT, TIME TEXT, PATH TEXT)"; 
           Stat.executeUpdate(sql);
           
           Stat.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
     }

}
