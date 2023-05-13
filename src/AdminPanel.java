
import java.util.ArrayList;
import java.util.Scanner;

import Admin.AdminCatalog.Category;
import Admin.AdminCatalog.MainCatalog;
import Admin.AdminCatalog.Product;
import Admin.AdminOrder.Order;
import Admin.AdminUser.RegUser;
import Admin.AdminOrder.OrderManager;
import Admin.AdminUser.UserManager;

public class AdminPanel {

    public static Scanner input = new Scanner(System.in);
    public static MainCatalog catalog = new MainCatalog();
    public static UserManager userManager = new UserManager();
    public static OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        
        System.out.println("Welcome to Admin Panel \n");
        
        while(true){
            System.out.println("\n Please choose an option: ");
            System.out.println("1. Manage Catalog");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Users");
            System.out.println("4. Exit");
            
            int choice;
            do{
                System.out.print("\nchoice: ");
                choice = input.nextInt();
        
                switch (choice) {
                    case 1:
                        manageCatalog();
                        break;
                    case 2:
                        manageOrders();
                        break;
                    case 3:
                        manageUsers();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        input.close();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
    
            }while(choice < 1 || choice > 4);
        
        }
            
    }

    public static void manageCatalog() {
        System.out.println("\n\nCatalog Management");
        System.out.println("1. Add Product");
        System.out.println("2. Add Category");
        System.out.println("3. View Products");
        System.out.println("4. Back");
        System.out.print("\nchoice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                //fill product details
                int productID;
                String name;
                String category;
                String itemDescription;
                String brand;
                float price;
                boolean isLoose;
                float discount;
                String status;
                int availableQuantity;

                System.out.print("Enter product ID: ");
                productID = input.nextInt();
                System.out.print("Enter product name: ");
                name = input.next();
                System.out.print("Enter product category: ");
                category = input.next();
                System.out.print("Enter product description: ");
                itemDescription = input.next();
                System.out.print("Enter product brand: ");
                brand = input.next();
                System.out.print("Enter product price: ");
                price = input.nextFloat();
                System.out.print("Enter product type (loose or packed): ");
                isLoose = input.nextBoolean();
                System.out.print("Enter product discount: ");
                discount = input.nextFloat();
                System.out.print("Enter product status: ");
                status = input.next();
                System.out.print("Enter product quantity: ");
                availableQuantity = input.nextInt();
                Product product = new Product(productID, name, category, itemDescription, brand, price, isLoose, discount, status, availableQuantity);
                catalog.addProduct(product);

                break;
            case 2:
                int categoryID;
                String categoryName;
                System.out.print("Enter category ID: ");
                categoryID = input.nextInt();
                System.out.print("Enter category name: ");
                categoryName = input.next();
                Category newCategory = new Category(categoryID, categoryName);
                catalog.createCategory(newCategory);
                break;
            case 3:
                ArrayList<Product> products = catalog.returnAll();
                for (Product p : products) {
                    //print product details
                    System.out.println("\n ################################## \n");
                    System.out.println("Product ID: " + p.getID());
                    System.out.println("Product Name: " + p.getName());
                    System.out.println("Product Category: " + p.getCategory());
                    System.out.println("Product Description: " + p.getDescription());
                    System.out.println("Product Brand: " + p.getBrand());
                    System.out.println("Product Price: " + p.getPrice());
                    System.out.println("Product Type: " + p.getType());
                    System.out.println("Product Status: " + p.getStatus());
                    System.out.println("Product Quantity: " + p.getAvailableQuantity());

                }
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void manageOrders() {
        System.out.println("\n\nOrder Management");
        System.out.println("1. View Orders");
        System.out.println("2. Close Order");
        System.out.println("3. Back");
        System.out.print("\nchoice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                ArrayList<Order> orders = orderManager.getOrders();
                //print order details
                for(Order ord : orders){
                    System.out.println("\n ################################## \n");
                    System.out.println("Order ID: " + ord.getOrderID());
                    System.out.println("Order Status: " + ord.getOrderStatus());
                    System.out.println("Order Date: " + ord.getOrderDate().getDay() + "/" + ord.getOrderDate().getMonth() + "/" + ord.getOrderDate().getYear());
                    System.out.println("Order Destination: " + ord.getOrderDestination());
                    System.out.println("Order Total Price: " + ord.getTotalPrice());
                    System.out.println("User ID: " + ord.getUserID());
                }
                break;
            case 2:
                System.out.print("\n Enter order ID: ");
                int orderID = input.nextInt();
                orderManager.closeOrder(orderID);
                System.out.println("Order closed successfully");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void manageUsers() {
        System.out.println("\n\nUser Management");
        System.out.println("1. View Users");
        System.out.println("2. Back");
        System.out.print("\nchoice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                ArrayList<RegUser> users = userManager.getUsers();
                //print user details
                for(RegUser user : users){
                    System.out.println("\n ################################## \n");
                    System.out.println("User ID: " + user.getUserID());
                    System.out.println("User Name: " + user.getUsername());
                    System.out.println("User Email: " + user.getUserEmail());
                    System.out.println("User Password: " + user.getUserPassword());
                    System.out.println("User Address: " + user.getUserAddress());
                    System.out.println("User Status: " + user.getUserStatus());
                    System.out.println("User Points: " + user.getPoints());
                }
                break;
            case 2:
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

}
