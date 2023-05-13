
import java.util.Scanner;

import Admin.*;

public class AdminPanel {

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("Welcome to Admin Panel");
        
        System.out.println("Please choose an option: ");
        System.out.println("1. Manage Catalog");
        System.out.println("2. Manage Orders");
        System.out.println("3. Manage Users");
        int choice = input.nextInt();
    
    }
}
