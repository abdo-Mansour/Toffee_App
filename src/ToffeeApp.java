import java.lang.reflect.Array;
import java.util.Scanner;

import Customer.CustomerAuthentication.*;
import Customer.CustomerCart.*;
import Customer.CustomerCatalog.*;
import Customer.CustomerOrder.*;
import Customer.CustomerPayment.*;
import Database.Db;
import java.util.ArrayList;

public class ToffeeApp {
    public static Scanner input = new Scanner(System.in);
    public static MainAuth mainAuth = new MainAuth(1);
    public static MainCatalog mainCatalog = new MainCatalog();
    public static OrderManager mainOrder;
    public static Cart mainCart;
    public static Db db = new Db();


    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Toffee App \n");
        db.connectServer();
        
        int choice;
        while(true){
            System.out.println("\n Please choose an option: ");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Enter Store");
            System.out.println("4. Exit");
            do{
                System.out.print("\nchoice: ");
                choice = input.nextInt();
        
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        runShop();
                        break;
                    case 4:
                        System.out.println("Thank You for using Toffee App!!");
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

    public static void login() {
        System.out.println("\nLogin");
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter password: ");
        String password = input.next();
        
        if(db.checkUser(username, password)){
            System.out.println("\nLogin Successful");
            System.out.println("Welcome " + username);
            mainAuth.setUser(db.getUserUsingUsername(username));
            //TODO: create user object
        }else{
            System.out.println("\n Invalid username or password");
        }
        
    }

    public static void register() {
        System.out.println("\nRegister");
        mainAuth.createRegUser();
    }

    public static void runShop() {
        System.out.println("\nWelcome to Toffee Store");
        System.out.println("0. Back");
        System.out.println("1. View Catalog");
        if(mainAuth.getRegUser() != null){
            System.out.println("2. View Cart");
            System.out.println("3. View Orders History");
        }

        int choice;
        do{
            System.out.print("\nchoice: ");
            choice = input.nextInt();
    
            switch (choice) {
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    viewOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice < 0 || choice > 3);
        

        
    }

    public static void viewCatalog(){
        ArrayList<Product> products = mainCatalog.returnAll();
        System.out.println("\nProducts:\n");
        int i = 0;
        if(mainAuth.getRegUser() != null){
            mainCart = new Cart(mainAuth.getRegUser());
        }
        for(Product prod : products){
            if(mainAuth.getRegUser() != null){
                prod.setCart(mainCart);
            }
            System.out.println(i + ".  Product:" + prod.getName() + "  price" + prod.getPrice());
            i++;
        }

        while(true){
            System.out.println("\nPlease write the id for the product you want to add to cart: ");
            System.out.println("Write -1 to go back");
            int choice = input.nextInt();
            if(choice == -1){
                break;
            }else if(choice >= 0 && choice < products.size()){
                System.out.println("Available quantity: " + products.get(choice).getAvailableQuantity());
                System.out.println("Please enter the quantity: ");
                Float quantity = input.nextFloat();
                if(quantity > products.get(choice).getAvailableQuantity()){
                    System.out.println("Quantity not available");
                }else{
                    products.get(choice).addToCart(quantity);
                }
            }
        }


    }

    public static void viewCart(){
        if(mainAuth.getRegUser() == null){
            System.out.println("Please login to view cart");
            return;
        }

        System.out.println("\nCart:\n");
        mainCart.printCart();
        System.out.println("\nTotal: " + mainCart.getTotal());
        System.out.println("\n1. Checkout");
        System.out.println("2. Back");
        int choice;
        do{
            System.out.print("\nchoice: ");
            choice = input.nextInt();
    
            switch (choice) {
                case 1:
                    mainCart.checkOut();
                    System.out.println("Order placed successfully");
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice < 1 || choice > 2);

    }

    public static void viewOrder(){
        if(mainAuth.getRegUser() == null){
            System.out.println("Please login to view orders");
            return;
        }
        mainOrder = mainAuth.getRegUser().getOrderManager();
        System.out.println("\nCurrent Order:\n");
        Order currentOrder = mainOrder.getOrder(mainOrder.getCurrentOrderID());
        currentOrder.printOrder();
        if(currentOrder != null){
        }

        System.out.println("\n1. Pay for Order. ");
        System.out.println("2. Cancel Order. ");
        System.out.println("3. Back. ");
        int choice;
        do{
            System.out.print("\nchoice: ");
            choice = input.nextInt();
    
            switch (choice) {
                case 1:
                    System.out.print("Please enter your phone number: ");
                    int phone = input.nextInt();
                    currentOrder.payOrder("Cash", phone);
                    break;
                case 2:
                    mainOrder.cancelOrder(mainOrder.getCurrentOrderID());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice < 1 || choice > 3);

    }
}
