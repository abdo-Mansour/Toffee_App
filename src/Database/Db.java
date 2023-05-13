package Database;

import java.sql.*;
import Customer.CustomerAuthentication.*;
import Customer.CustomerCatalog.*;
import Customer.CustomerOrder.*;
import java.util.ArrayList;

public class Db {
    private String DATABASE_NAME = "Toffee";
    private String DATABASE_USERNAME = "root";
    private String DATABASE_PASSWORD = "toffee";
    private Connection mainConnection;

    public void connectServer() {
        // System.out.println("database connection test");
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=";
            url += DATABASE_NAME;
            url += ";encrypt=false";

            mainConnection = DriverManager.getConnection(url, DATABASE_USERNAME, DATABASE_PASSWORD);
            
            System.out.println("database connection successful");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("database connection exception");
        }
    }

    /*
     * on log in we will read from database
     * on sign up will read first to check then write
     * on display category and products we will read from database
     * on adding category and products we will read first to check then write
     * on adding orders we will write
     */
    // checking database functions
    public boolean checkUser(String username, String password) {
        return true;

    }

    // writing database functions
    public void addUser(RegUser user) {
        try {
            PreparedStatement stmt = mainConnection.prepareStatement("INSERT INTO User (UserID,Name, Email, Password,Adress) VALUES (?,?, ?, ?,?)");
            stmt.setString(1, Integer.toString(user.getUserID()));
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getUserPassword());
            stmt.setString(4, user.getUserEmail());
            stmt.setString(5, user.getUserAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    public void addOrder(Order order) {

    }

    public void addProduct(Product product) {

    }

    public void addCategory(Category category) {

    }

    // reading database functions
    public ArrayList<Category> getCategories() {
        return null;
    }

    public ArrayList<Product> getProductWithCategory(String category) {
        return null;
    }

    public ArrayList<Product> getProductWithName(String name) {

        return null;
    }

    public ArrayList<Product> getProductWithBrand(String brand) {
        return null;
    }

    public ArrayList<Order> getOrders(RegUser user) {
        return null;
    }

    public Order getOrderWithId(int id) {
        return null;
    }

    public Product getProductWithId(int id) {
        return null;
    }

    public RegUser getUserWithId(int id) {
        return null;
    }

    // for testing
    public static void main(String[] args) {
        Db db = new Db();
        db.connectServer();
        RegUser user = new RegUser(1, 1, "amr", "amrkhaled123@toffee.com", "amr123", "qwerty");
        db.addUser(user);
    }
}
