import java.sql.*;
import java.io.*;
import java.util.Scanner;
public class OpSqliteDB {
    
   public static void main(String args[]) {
        // load the sqlite-JDBC driver using the current class loader
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c=DriverManager.getConnection("jdbc:sqlite:Movies.db");
            
            System.out.println("Success!");
            Statement statement = c.createStatement();
            
            System.out.println("10 record from directors");
            ResultSet rs = statement.executeQuery("select * from titles limit 10");
            while (rs.next()) {
               String col1 = rs.getString("title");
               String col2 = rs.getString("type");
               String col3 = rs.getString("director");
               String col7 = rs.getString("rating");
               String col8 = rs.getString("duration");
               System.out.println(  col1 + " " + col2 + " " + col3 +
            		  " " + col7 + " " + col8);
            }

            System.out.println("\n 10 titles from directors");
            ResultSet rs1 = statement.executeQuery("select title from directors limit 10");
            while (rs1.next()) {
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
