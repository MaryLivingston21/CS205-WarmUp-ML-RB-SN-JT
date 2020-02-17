import java.sql.*;
import java.io.*;
import java.util.Scanner;
public class OpSqliteDB
{
    public static Connection c;
    // Default Constructor
    public void OpSqliteDB()
    {
      // Make Conection
      Connection c = null;
    }

    public static Connection connect()
    {
        // connect the sqlite-JDBC driver using the current class loader
        try
        {
           Class.forName("org.sqlite.JDBC");
           c=DriverManager.getConnection("jdbc:sqlite:src/Film.db");
                   
        } catch(Exception e) {
        
            e.printStackTrace();
        } 
        return c;
    }
    
    public static void createTables()
    {
        try
        {
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec("python src/load.py");
           BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));                
           input.close();                                                                                                       
                                   
        } catch(Exception e) {        
           e.printStackTrace();

        } 
    
    }
    
    // TODO modify query function takes parameters as three parses in
    // TODO Make quries    
    // 1st be attribute, hwta user whats(output)
    // 2nd be user input type
    // 3rd detail of input
    
    public static void query(String output_t, String input, String detail)
    {
       try
       {
           Statement statement = c.createStatement(); 
           String v2 = "directors"; // default table
           
           switch (output_t) {
           // 2 cases for titles only
           case "date_added": v2 = "titles"; break;
           case "description": v2 = "titles"; break;
           // 3 cases for directors only
           case "actor": v2 = "directors"; break;
           case "country": v2 = "directors"; break;
           case "release_year": v2 = "directors"; break;
           // Shared Cases
   //         case "rating": v2 = "directors"; break;
//            case "duration": v2 = "directors"; break;
//            case "title": v2 = "directors"; break;
            case "director": v2 = "directors"; break;
           
           }
           //Now for when their output_t is in both tables, lets try to do the query 
           //and if there's a error, to try te other table
           
          
           //this formating for inputs is working 
           String query = "select " + output_t + " from " + v2 + " where " + input + " = \"" + detail + "\";" ;
           // System.out.println(query); testing
           ResultSet rs = statement.executeQuery(query);
           while (rs.next()) {
              String col1 = rs.getString(output_t);
              System.out.println(output_t + " " + col1);
           }
        
       } catch (SQLException e) {
           
           System.err.println(e.getMessage());
           
       } catch(Exception e) {
           
           e.printStackTrace();
           
       } 
    }

    public static void dropTables()
    {
      try{
         Statement statement = c.createStatement();                      
         statement.executeUpdate("drop table if exists directors;");
         statement.executeUpdate("drop table if exists titles;");
         
      } catch (SQLException e) {        
         System.err.println(e.getMessage());
     
      } catch(Exception e) {     
          e.printStackTrace();
      }  
    }             
} 