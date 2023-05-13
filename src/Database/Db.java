package Database;

import java.sql.*;
import Customer.CustomerAuthentication.*;
import Customer.CustomerCatalog.*;
import Customer.CustomerOrder.*;
import java.util.ArrayList;
import Customer.CustomerCart.*;

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
            Statement stmt = mainConnection.createStatement();
            ResultSet rs = stmt.executeQuery("USE Toffee");
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
            PreparedStatement stmt = mainConnection
                    .prepareStatement(
                            "INSERT INTO \"User\" (UserID,Name, Email, Password,Address) VALUES (?,?, ?, ?,?)");
            stmt.setString(1, Integer.toString(user.getUserID()));
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getUserEmail());
            stmt.setString(4, user.getUserPassword());
            stmt.setString(5, user.getUserStatus());
            stmt.setString(5, Integer.toString(user.get));
            stmt.setString(5, user.getUserAddress());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    public void addOrder(Order order) {
        // try {
        //     PreparedStatement stmt = mainConnection
        //             .prepareStatement(
        //                     "INSERT INTO \"User\" (UserID,Name, Email, Password,Address) VALUES (?,?, ?, ?,?)");
        //     stmt.setString(1, Integer.toString(Order.getUserID()));
        //     stmt.setString(2, user.getUsername());
        //     stmt.setString(3, user.getUserEmail());
        //     stmt.setString(4, user.getUserPassword());
        //     stmt.setString(5, user.getUserAddress());
        //     stmt.executeUpdate();
        // } catch (SQLException e) {
        //     System.err.println("Error adding user: " + e.getMessage());
        // }
    }

    public void addProduct(Product product) {
        try {
            PreparedStatement stmt = mainConnection
                    .prepareStatement(
                            "INSERT INTO \"Product\" (ProductID,Name,Quantity,itemDescription,Brand,Status,isLoose,Price,Discount,CatagoryID) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, Integer.toString(product.getID()));
            stmt.setString(2, product.getName());
            stmt.setString(3, Integer.toString(product.getAvailableQuantity()));
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getBrand());
            stmt.setString(6, product.getStatus());
            stmt.setString(7, Boolean.toString(product.getType()));
            stmt.setString(8, Float.toString(product.getPrice()));
            stmt.setString(9, Float.toString(product.getDiscount()));
            stmt.setString(10, Integer.toString(product.getCategory().getID()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    public void addCategory(Category category) {
        try {
            PreparedStatement stmt = mainConnection
                    .prepareStatement("INSERT INTO \"Catagory\" (CatagoryID,Name) VALUES (?,?)");
            stmt.setString(1, Integer.toString(category.getID()));
            stmt.setString(2, category.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
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

    public ArrayList<RegUser> getUsers() {
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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RegUser user = null;

        try {

            // Prepare a SQL statement that selects a user with the given ID
            statement = connection.prepareStatement("SELECT * FROM User WHERE id = ?");
            statement.setInt(1, id);

            // Execute the statement and get the result set
            resultSet = statement.executeQuery();

            // If a user with the given ID is found, create a RegUser object to represent it
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new RegUser(userId, name, email, password);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user with ID " + id + " from database: " + e.getMessage());
        } 
        return user;
    }

    // for testing
    public static void main(String[] args) {
        Db db = new Db();
        db.connectServer();
        ArrayList<Product> products = new ArrayList<Product>();
        RegUser user = new RegUser(1,1, "name", "email", "password", "address");
        Cart cart = new Cart(user);
        Product p = new Product(1, "name", new Category(1, "name",products),"description",  "brand", 1.0, true,1.0, "status",cart,  1);
    }
}