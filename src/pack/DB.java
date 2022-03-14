package pack;
import java.sql.*;

import org.sqlite.SQLiteDataSource;

public class DB {
	static Connection c = null; 
    public static void main(String[] args) {

             
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:DB.db");
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Opened database successfully");
        
        
        XML XMLobj = new XML();
        XMLobj.GetFile("FAKESTUDY001.xml");
        MakeTables();
        
    }
    
    
    private static void MakeTables() {
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
