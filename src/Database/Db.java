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
            // System.out.println(e);
            System.out.println("database connection exception");
        }
    }

    public void closeConnection() {
        try {
            mainConnection.close();
        } catch (Exception e) {
            // System.out.println(e);
        }
    }

    /*
     * on log in we will read from database
     * on sign up will read first to check then write
     * on display category and products we will read from database
     * on adding category and products we will read first to check then write
     * on adding orders we will write
     */
    //function that executes a query to change the database
    public void executeQuery(String query) {
        try {
            Statement stmt = mainConnection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            // System.out.println(e);
        }
    }
    //returns the size of the database
    public int getSize(String tableName) {
        int size = 0;
        try {
            Statement stmt = mainConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            rs.next();
            size = rs.getInt(1);
        } catch (Exception e) {
            // System.out.println(e);
        }
        return size;
    }
    // checking database functions
    public boolean checkUser(String username, String password) {
        try {
            // Prepare a SQL statement that selects a user with the given username and
            // password
            PreparedStatement statement = mainConnection.prepareStatement(
                    "SELECT * FROM \"User\" WHERE Name = ? AND Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // If a user with the given username and password is found, return true
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            //System.err.println("Error checking user: " + e.getMessage());
        }

        // If no user with the given username and password is found, return false
        return false;
    }

    // writing database functions
    public void addUser(RegUser user) {
        try {
            PreparedStatement stmt = mainConnection
                    .prepareStatement(
                            "INSERT INTO \"User\" (UserID,Name, Email, Password,Address,Status,LoyaltyPoints) VALUES (?,?, ?, ?,?,?,?)");
            stmt.setString(1, Integer.toString(user.getUserID()));
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getUserEmail());
            stmt.setString(4, user.getUserPassword());
            stmt.setString(5, user.getUserAddress());
            int tempStat;
            if(user.getUserStatus() == "Active"){
                tempStat = 1;
            }
            else{
                tempStat = 0;
            }
            stmt.setString(6, Integer.toString(tempStat));
            stmt.setString(7, Integer.toString(user.getLoyaltyPoints()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }

    public void addOrder(Order order) {
        try {
            
            // Prepare a SQL statement that inserts an order into the database
            PreparedStatement statement = mainConnection
                    .prepareStatement("INSERT INTO \"Orders\" (orderID, userID, orderStatus, orderDate, totalPrice, orderDestination) VALUES (?,?,?,?,?,?)");

            // Set the values of the parameters in the SQL statement
            statement.setString(1, Integer.toString(order.getID()));
            statement.setString(2, Integer.toString(order.getUser().getUserID()));
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getDate().getDay() + "/" + order.getDate().getMonth() + "/"
                    + order.getDate().getYear());
            statement.setString(5, Float.toString(order.getTotal()));
            statement.setString(6, order.getDestination());

            // Execute the SQL statement
            statement.executeUpdate();

        } catch (SQLException e) {
            //System.err.println("Error adding order: " + e.getMessage());
        }
    }

    public void addProduct(Product product) {
        try {
            PreparedStatement stmt = mainConnection
                    .prepareStatement(
                            "INSERT INTO \"Product\" (ProductID,Name,Quantity,itemDescription,Brand,Status,isLoose,Price,Discount,CatagoryID) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, Integer.toString(product.getID()));
            stmt.setString(2, product.getName());
            stmt.setString(3, Float.toString(product.getAvailableQuantity()));
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getBrand());
            stmt.setString(6, product.getStatus());
            stmt.setString(7, Boolean.toString(product.getType()));
            stmt.setString(8, Float.toString(product.getPrice()));
            stmt.setString(9, Float.toString(product.getDiscount()));
            stmt.setString(10, Integer.toString(product.getCategory().getID()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            //System.err.println("Error adding user: " + e.getMessage());
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

    //Category
    // reading database functions
    public ArrayList<Category> getCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            // Prepare a SQL statement that selects all categories
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM Catagory");

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create a Category object for each row
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("CatagoryID");
                String name = resultSet.getString("Name");
                Category category = new Category(categoryId, name, getProductWithCategoryID(categoryId));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving categories from database: " + e.getMessage());
        }
        return categoryList;
    }

    public ArrayList<Product> getProductWithCategoryID(int categoryID) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            // Prepare a SQL statement that selects products with the given category
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM Product WHERE CatagoryID = ?");
            statement.setString(1, Integer.toString(categoryID));

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create a Product object for each row
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String itemDescription = resultSet.getString("itemDescription");
                String status = resultSet.getString("Status");
                boolean isLoose = resultSet.getBoolean("isLoose");
                String brand = resultSet.getString("Brand");
                Float price = resultSet.getFloat("Price");
                Float discount = resultSet.getFloat("Discount");
                int categoryId = resultSet.getInt("CatagoryID");
                Product product = new Product(productId, name, itemDescription, brand, price, isLoose, discount, status,
                        quantity);
                productList.add(product);
            }
        } catch (SQLException e) {
            //System.err.println( "Error retrieving products with category " + categoryID + " from database: " + e.getMessage());
        }
        return productList;
    }

    public ArrayList<Product> getProductWithName(String name) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            // Prepare a SQL statement that selects products with the given name
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM Product WHERE Name = ?");
            statement.setString(1, name);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create a Product object for each row
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                int quantity = resultSet.getInt("Quantity");
                String itemDescription = resultSet.getString("itemDescription");
                String brand = resultSet.getString("Brand");
                String status = resultSet.getString("Status");
                boolean isLoose = resultSet.getBoolean("isLoose");
                Float price = resultSet.getFloat("Price");
                Float discount = resultSet.getFloat("Discount");
                int categoryId = resultSet.getInt("CatagoryID");
                Product product = new Product(productId, name, itemDescription, brand, price, isLoose, discount, status,
                        quantity);
                productList.add(product);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving products with name " + name + " from database: " + e.getMessage());
        }
        return productList;
    }

    public ArrayList<Product> getProductWithBrand(String brand) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            // Prepare a SQL statement that selects products with the given brand
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM Product WHERE Brand = ?");
            statement.setString(1, brand);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create a Product object for each row
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String itemDescription = resultSet.getString("itemDescription");
                String status = resultSet.getString("Status");
                boolean isLoose = resultSet.getBoolean("isLoose");
                Float price = resultSet.getFloat("Price");
                Float discount = resultSet.getFloat("Discount");
                int categoryId = resultSet.getInt("CatagoryID");
                Product product = new Product(productId, name, itemDescription, brand, price, isLoose, discount, status,
                        quantity);
                productList.add(product);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving products with brand " + brand + " from database: " + e.getMessage());
        }
        return productList;
    }

    public ArrayList<RegUser> getUsers() {
        ArrayList<RegUser> users = new ArrayList<RegUser>();
        try {
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM \"User\"");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String address = resultSet.getString("Address");
                RegUser user = new RegUser(1, userId, name, email, password, address);
                users.add(user);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving users from database: " + e.getMessage());
        }
        return users;
    }

    // public ArrayList<Order> getOrders(RegUser user) {
    //     ArrayList<Order> orders = new ArrayList<Order>();
    //     try {
    //         // Prepare a SQL statement that selects orders for the given user
    //         PreparedStatement statement = mainConnection.prepareStatement(
    //                 "SELECT * FROM \"Order\" JOIN \"User\" ON \"Order\".UserID = \"User\".UserID WHERE \"User\".UserID = ?");
    //         statement.setInt(1, user.getUserID());

    //         // Execute the statement and get the result set
    //         ResultSet resultSet = statement.executeQuery();

    //         // For each row in the result set, create an Order object to represent the order
    //         // and add it to the list
    //         while (resultSet.next()) {
    //             int orderId = resultSet.getInt("OrderID");
    //             Date date = resultSet.getDate("Date");
    //             int userId = resultSet.getInt("UserID");
    //             Order order = new Order(orderId, date, userId);
    //             orders.add(order);
    //         }
    //     } catch (SQLException e) {
    //         System.err.println(
    //                 "Error retrieving orders for user " + user.getUsername() + " from database: " + e.getMessage());
    //     }
    //     return orders;
    // }

    // public ArrayList<Order> getOrder_items(Order order, Product product) {
    //     ArrayList<Order> orders = new ArrayList<Order>();
    //     try {
    //         // Prepare a SQL statement that selects orders for the given user
    //         PreparedStatement statement = mainConnection.prepareStatement(
    //                 "SELECT * FROM \"Order\" JOIN \"User\" ON \"Order\".UserID = \"User\".UserID WHERE \"User\".UserID = ?");
    //         statement.setInt(1, user.getUserID());

    //         // Execute the statement and get the result set
    //         ResultSet resultSet = statement.executeQuery();

    //         // For each row in the result set, create an Order object to represent the order
    //         // and add it to the list
    //         while (resultSet.next()) {
    //             int orderId = resultSet.getInt("OrderID");
    //             Date date = resultSet.getDate("Date");
    //             int userId = resultSet.getInt("UserID");
    //             Order order = new Order(orderId, date, userId);
    //             orders.add(order);
    //         }
    //     } catch (SQLException e) {
    //         System.err.println(
    //                 "Error retrieving orders for user " + user.getUsername() + " from database: " + e.getMessage());
    //     }
    //     return orders;
    // }

    // public Order getOrderWithId(int id) {
    //     Order order = null;
    //     try {
    //         // Prepare a SQL statement that selects an order with the given ID
    //         PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM \"Order\" WHERE OrderID = ?");
    //         statement.setInt(1, id);

    //         // Execute the statement and get the result set
    //         ResultSet resultSet = statement.executeQuery();

    //         // If an order with the given ID is found, create an Order object to represent
    //         // it
    //         if (resultSet.next()) {
    //             int orderId = resultSet.getInt("OrderID");
    //             Date date = resultSet.getDate("Date");
    //             int userId = resultSet.getInt("UserID");
    //             order = new Order(orderId, date, userId);
    //         }
    //     } catch (SQLException e) {
    //         System.err.println("Error retrieving order with ID " + id + " from database: " + e.getMessage());
    //     }
    //     return order;
    // }

    public Product getProductWithId(int id) {
        Product product = null;
        try {
            // Prepare a SQL statement that selects a product with the given ID
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?");
            statement.setInt(1, id);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // If a product with the given ID is found, create a Product object to represent
            // it
            if (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                String itemDescription = resultSet.getString("itemDescription");
                String brand = resultSet.getString("Brand");
                String status = resultSet.getString("Status");
                boolean isLoose = resultSet.getBoolean("isLoose");
                Float price = resultSet.getFloat("Price");
                Float discount = resultSet.getFloat("Discount");
                int categoryId = resultSet.getInt("CatagoryID");
                product = new Product(productId, name, itemDescription, brand, price, isLoose, discount, status,
                        quantity);
            }
        } catch (SQLException e) {
           // System.err.println("Error retrieving product with ID " + id + " from database: " + e.getMessage());
        }
        return product;
    }

    public RegUser getUserWithId(int id) {
        RegUser user = null;
        try {

            // Prepare a SQL statement that selects a user with the given ID
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM \"User\" WHERE UserID = ?");
            statement.setInt(1, id);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // If a user with the given ID is found, create a RegUser object to represent it
            if (resultSet.next()) {
                int userId = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String Adress = resultSet.getString("Address");
                String Status = resultSet.getString("Status");
                user = new RegUser(1, userId, name, email, password, Adress);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving user with ID " + id + " from database: " + e.getMessage());
        }
        return user;
    }

    public RegUser getUserUsingUsername(String username){
        RegUser user = null;
        try {

            // Prepare a SQL statement that selects a user with the given ID
            PreparedStatement statement = mainConnection.prepareStatement("SELECT * FROM \"User\" WHERE Name = ?");
            statement.setString(1, username);

            // Execute the statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // If a user with the given ID is found, create a RegUser object to represent it
            if (resultSet.next()) {
                int userId = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String Adress = resultSet.getString("Address");
                String Status = resultSet.getString("Status");
                user = new RegUser(1, userId, name, email, password, Adress);
            }
        } catch (SQLException e) {
            //System.err.println("Error retrieving user with ID " + username + " from database: " + e.getMessage());
        }
        return user;
    } 

    // for testing

}