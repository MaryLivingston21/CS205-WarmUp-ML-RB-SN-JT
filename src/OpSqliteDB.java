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


    public static void query(String output_t, String type1, String input1, String type2, String input2) {
        String result = "";
        try{
            String col1 = "";
            Statement statement = c.createStatement();
            String table = "directors"; // default table
            getTableName(table);
//            rating country India director A. L. Vijay
//  select titles.rating from directors, titles where directors.director = titles.director and directors.country = 'India' and directors.director = 'A. L. Vijay' ;
            String query = "select " + table + "."+ output_t + " from " + "directors, titles" + "where d.netflix_titles = t.titles and " + type1 +
                                " = \"" + input1  + " and " + type2 + " = \"" + input2 + "\";" ;
            // System.out.println(query); testing
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                col1 = rs.getString(output_t);
                System.out.println(output_t + " " + col1);
            }
            result = output_t + "\t " + col1;


        } catch (SQLException e) {

            System.err.println(e.getMessage());

        } catch(Exception e) {

            e.printStackTrace();

        }
    }


    private static String getTableName(String output_t) {
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
        return v2;
    }

    // TODO modify query function takes parameters as three parses in
    // TODO Make quries    
    // 1st be attribute, hwta user whats(output)
    // 2nd be user input type
    // 3rd detail of input
    
    public static String query(String output_t, String input, String detail)
    {
        String result ="";
       try
       {
           String col1 = "";
           Statement statement = c.createStatement();
           
           String v2 = getTableName(output_t);
           //Now for when their output_t is in both tables, lets try to do the query 
           //and if there's a error, to try te other table
           
          
           //this formating for inputs is working 
           String query = "select " + output_t + " from " + v2 + " where " + input + " = \"" + detail + "\";" ;
           // System.out.println(query); testing
           ResultSet rs = statement.executeQuery(query);
           while (rs.next()) {
              col1 = rs.getString(output_t);
              System.out.println(output_t + " " + col1);
           }
           result = output_t + "\t " + col1;

       } catch (SQLException e) {
           
           System.err.println(e.getMessage());
           
       } catch(Exception e) {
           
           e.printStackTrace();
           
       }
        return result;
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