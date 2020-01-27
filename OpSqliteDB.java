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
    

/*
    public static void func1(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from table_name1");
        while (rs.next()) {
            String col1 = rs.getString("col1_name");
            String col2 = rs.getString("col2_name");
            System.out.println("col1 = " + col1 + "  col2 = " + col2);
            
           //System.out.println(location);
            // 执行插入语句操作
            statement1.executeUpdate("insert into table_name2(col2) values('" + col2_value + "')");
            // 执行更新语句
            statement1.executeUpdate("update table_name2 set 字段名1=" +  字段值1 + " where 字段名2='" +  字段值2 + "'");
        }
    }
   */
   
}