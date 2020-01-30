import java.sql.*;
import java.io.*;
import java.util.Scanner;
public class OpSqliteDB 
{
    
   public static void main(String args[]) 
   {
        Boolean load = true;
        // User prompt
                        
        // create Table
        if (load)
        {
           createTable();    
        }   

        // Make Conection 
        Connection c = null;
        c = connect(c);
        
        // Query from table
        // query(c);

    }
    
    public static Connection connect(Connection c)
    {
        // connect the sqlite-JDBC driver using the current class loader
        try 
        {   
           Class.forName("org.sqlite.JDBC");
           c=DriverManager.getConnection("jdbc:sqlite:Film.db");                                    
                   
        } catch(Exception e) {
        
            e.printStackTrace();
        } 
        return c;
    }
    
    public static void createTable() 
    {
        try 
        {                 
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec("python load.py");               
           BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));                
           input.close();                                                                                                       
                                   
        } catch(Exception e) {        
           e.printStackTrace();
        } 
    
    }
    
    public static void query(Connection c) 
    {
       try
       {       
           Statement statement = c.createStatement(); 
           ResultSet rs = statement.executeQuery("select * from directors limit 10");
           while (rs.next()) {
              String col1 = rs.getString("title");
              System.out.println("title = " + col1);
           }
        
       } catch (SQLException e) {
           
           System.err.println(e.getMessage());
           
       } catch(Exception e) {
           
           e.printStackTrace();
           
       } 
    }
} 