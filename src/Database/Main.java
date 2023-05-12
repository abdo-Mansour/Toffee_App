package Database;

import java.sql.*;

public class Main{
    public static void main(String[] args) {
        System.out.println("database connection test");
        try {
            String  databaseName= "Toffee";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String databaseUsername = "root";
            String databasePassword = "toffee";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=";
            url += databaseName;
            url += ";encrypt=false";
            System.out.println(url);
            Connection connection= DriverManager.getConnection(url, databaseUsername, databasePassword);

            System.out.println("database connection successful");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("database connection exception");
        }
    }
}