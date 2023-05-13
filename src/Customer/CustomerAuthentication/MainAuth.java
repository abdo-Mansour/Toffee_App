package Customer.CustomerAuthentication;


import java.util.Scanner;
import java.util.Random;

public class MainAuth {
    private int sessionID;
    private static int userID = 0;
    private GuestUser guestUser;
    private RegUser regUser;


    public MainAuth(int sessionID){
        this.sessionID = sessionID;
        guestUser = new GuestUser(sessionID);

    }

    public void createNewUser(){
        this.guestUser = new GuestUser(this.sessionID);
    }

    public void createRegUser(){
        this.guestUser = null;
        String username , userEmail , userPassword , userAddress;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your username: ");
        username = input.nextLine();
        
        System.out.println("Enter your email: ");
        userEmail = input.nextLine();
        if(!checkEmail(userEmail)){
            System.out.println("Invalid OTP");
            return;
        }

        do{
            System.out.println("\n \nPassword must contain at least one digit [0-9].");
            System.out.println("Password must contain at least one lowercase Latin character [a-z].");
            System.out.println("Password must contain at least one uppercase Latin character [A-Z].");
            System.out.println("Password must contain at least one special character like ! @ # & ( ).");
            System.out.println("Password must contain a length of at least 8 characters and a maximum of 20 characters.");
            System.out.println("Enter your password: ");
            userPassword = input.nextLine();
            if(!checkPassword(userPassword)){
                System.out.println("Invalid password");
            }
        }while(!checkPassword(userPassword));

        System.out.println("Enter your address: ");
        userAddress = input.nextLine();

        this.regUser = new RegUser(this.sessionID,generateUserID(),username,userEmail,userPassword,userAddress);
        storeUserInfo(this.regUser);
        //input.close();

    }

    public boolean checkPassword(String pass){
        
        // Password must contain at least one digit [0-9].
        // Password must contain at least one lowercase Latin character [a-z].
        // Password must contain at least one uppercase Latin character [A-Z].
        // Password must contain at least one special character like ! @ # & ( ).
        // Password must contain a length of at least 8 characters and a maximum of 20 characters.
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$";
        
        return(pass.matches(pattern));

    }

    public boolean checkEmail(String email){
        int code = generateCode();
        SendMail sendMail = new SendMail(email,code);
        sendMail.send();
        
        Scanner otp = new Scanner(System.in);
        
        System.out.println("Enter the code sent to your email: ");

        int codeEntered = otp.nextInt();
        //otp.close();
        if(codeEntered == code){
            return true;
        }
        else{
            return false;
        }

    }

    public static boolean checkPhone(int phoneNum){
        return true;
    }

    

    private boolean storeUserInfo(RegUser user){
        //TODO : DATABASE
        return true;
    }

    private int generateUserID(){
        return userID++;
    }

    private int generateCode(){
        //random code 4 digits 
        Random rand = new Random();
        int code = rand.nextInt(9999);
        return code;
    }

    public RegUser getRegUser(){
        return this.regUser;
    }

    public void setUser(RegUser user){
        this.regUser = user;
    }

}

