import java.sql.*;
import java.io.*;
import java.util.Scanner;
public class OpSqliteDB {
    
   public static void main(String args[]) {
        // load the sqlite-JDBC driver using the current class loader
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:Films.db");
            
            System.out.println("Success!");
            Statement statement = c.createStatement();
            
            ResultSet rs = statement.executeQuery("select title from film");
            while (rs.next()) {
               String col1 = rs.getString("title");
               System.out.println("title = " + col1);
            }
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }
}    
