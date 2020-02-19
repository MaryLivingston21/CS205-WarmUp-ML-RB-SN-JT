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
        try {
           Class.forName("org.sqlite.JDBC");
           c=DriverManager.getConnection("jdbc:sqlite:src/Film.db");

        } catch(Exception e) {
            e.printStackTrace();
        } 
        return c;
    }
    
    public static void createTables()
    {
        try {
           Runtime rt = Runtime.getRuntime();
           Process pr = rt.exec("python3 src/load.py");   // make sure that your python3 can be run in your shell // pip pandas is installed.
           BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
           input.close();
        } catch(Exception e) {        
           e.printStackTrace();
        }
    }
    
    // @param String what user want to get output type;
    // @param String input1 type;
    // @param String input1;
    // @param String input2 type2;
    // @param String input2
    public static void query(String output_t, String type1, String input1, String type2, String input2) {
        try{
            String table0 = "";
            String table1 = "";
            String table2 = "";
            table0 = getTableName(output_t);
            table1 = getTableName(type1);
            table2 = getTableName(type2);

            Statement statement = c.createStatement();
            String query = "select " + table0 + "."+ output_t + " from " + "directors, titles" + " where directors.netflix_id = titles.show_id and "  + table1 + "." + type1 +
                    " = \"" + input1  + "\" and " + table2 + "." + type2 + " = \"" + input2 + "\";" ;

            // System.out.println(query); // testing
            ResultSet rs = statement.executeQuery(query);
            printResult(rs, output_t);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // @param type: attribute's name
    // @return String table's name
    private static String getTableName(String type) {
        //Now for when their output_t is in both tables, lets try to do the query
        //and if there's a error, to try te other table
        String table = "directors"; // default table
        switch (type) {
            // 2 cases for titles only
            case "date_added": table = "titles"; break;
            case "description": table = "titles"; break;
            case "show_id" : table = "titles"; break;
            // 3 cases for directors only
            case "actor": table = "directors"; break;
            case "country": table = "directors"; break;
            case "release_year": table = "directors"; break;
            case "netflix_id" : table = "directors"; break;
            // Shared Cases
            //         case "rating": table = "directors"; break;
            //         case "duration": table = "directors"; break;
            //         case "title": table = "directors"; break;
            case "director": table = "directors"; break;

        }
        return table;
    }

    // @param 1st be attribute, hwta user whats(output)
    // @param 2nd be user input type
    // @param 3rd detail of input
    public static void query(String output_t, String input, String detail)
    {
       try
       {
           Statement statement = c.createStatement();
           
           String v2 = getTableName(output_t);
           String v22 = getTableName(input);
           boolean join = false;
           if (v2 != v22) {
               join = true;
           }

           //this formating for inputs is working
           String query = "";
           if (!join) {
               query = "select " + output_t + " from " + v2 + " where " + input + " = \"" + detail + "\";";
           }
           else {
               query = "select " + v2 + "." + output_t + " from " + v2 + "," + v22 + " where directors.netflix_id = titles.show_id and " + v22 + "."+ input + " = \"" + detail + "\";";
           }
           // System.out.println(query); testing
           ResultSet rs = statement.executeQuery(query);

           printResult(rs, output_t);

       } catch (SQLException e) {
           System.err.println(e.getMessage());
           
       } catch(Exception e) {
           e.printStackTrace();
       }
    }

    // @param ResultSet rs
    // @pram String output type
    public static void printResult(ResultSet rs, String output_t) {
        try {
            String result = "";
            while (rs.next()) {
                result = rs.getString(output_t);
                System.out.println("----------------------------------");
                System.out.println(output_t + "\t|\t" + result);
            }
            if (result.equals("")) {
                System.out.println("----------------------------------");
                System.out.println("Found nothing");
            }
            System.out.println("----------------------------------");
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }
} 