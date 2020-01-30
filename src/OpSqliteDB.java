import java.sql.*;
import java.io.*;
import java.util.Scanner;
public class OpSqliteDB 
{
    
   public static void main(String args[]) 
   {
        Boolean load = true;
        // User prompt
        
        
        // Make Conection 
        Connection c = null;
        c = connect(c);
        
        //create Table
        if (load)
        {
           createTable(c);    
        }   
        
        dropTables(c);

    }
    
    public static Connection connect(Connection c)
    {
        // connect the sqlite-JDBC driver using the current class loader
        try 
        {   
           Class.forName("org.sqlite.JDBC");
           c=DriverManager.getConnection("jdbc:sqlite:Test.db");                                    
                   
        } catch(Exception e) {
        
            e.printStackTrace();
        } 
        return c;
    }
    
    public static void createTable(Connection c) 
    {
        try 
        {               
            Statement statement = c.createStatement(); 

            // create tables
            statement.executeUpdate("create table directors( " +
               "show_id	integer, " +
               "type text, " +	
               "title primary key," +
               "director, " +	
               "cast	text, " +
               "country	text, " +
               "release_year integer, " +
               "rating text, " +
               "duration text); ");
               
            statement.executeUpdate("create table titles( " +
               "show_id	integer, " +
               "type text, " +	
               "title primary key, " +
               "director text, " +	
               "date_added text, " +	
               "duration text, " +	
               "description text ); ");
            
            // TODO insert tuples from csv to tabless
            Scanner scanner = new Scanner(new File("../netflix_directors.csv"));
            scanner.useDelimiter(",");
            while(scanner.hasNext())
            {               
               System.out.print(scanner.next()+"|");               
            }
            scanner.close();            
                                   
            statement.executeUpdate("insert into directors values(81155784, " +	
            "'Movie',	'Watchman',	'A. L. Vijay',"	+
            "'G.V. Prakash Kumar, Samyuktha Hegde, Suman, Raj Arjun, Yogi Babu, Munishkanth'," +
            "'India',	2019,	'TV-14',	'93 min');");
                        
            // query from tables  
            //statement.executeQuery()

        } catch (SQLException e) {
        
            System.err.println(e.getMessage());
        
        } catch(Exception e) {
        
            e.printStackTrace();
        } 
    
    }
    
    public static void dropTables(Connection c)
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