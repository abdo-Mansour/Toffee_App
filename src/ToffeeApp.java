import java.util.Scanner;

import Customer.CustomerAuthentication.*;
import Customer.CustomerCart.*;
import Customer.CustomerCatalog.*;
import Customer.CustomerOrder.*;
import Customer.CustomerPayment.*;
import Database.Db;

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
                    viewOrders();
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

    }

    public static void viewCart(){
        mainCart = new Cart(mainAuth.getRegUser());
    }

    public static void viewOrders(){
        mainOrder = new OrderManager(mainAuth.getRegUser());
    }
}
