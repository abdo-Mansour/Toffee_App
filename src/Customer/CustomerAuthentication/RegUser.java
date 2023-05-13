package Customer.CustomerAuthentication;

import Customer.CustomerCart.Cart;
public class RegUser {
    
    private int userID;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String userStatus;
    private Cart userCart;
    private OrderManager orderManager;
    private int loyaltyPoints;

    

    //constructor
    RegUser(int sessionID, int userID, String username, String userEmail, String userPass, String userAddress){
        this.userID = userID;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPass;
        this.userAddress = userAddress;
        this.userStatus = "Active";
        this.userCart = new Cart(this);
        this.loyaltyPoints = 0;
        sendData();
    }

    private void sendData(){
        //TODO: send data to database
    }

    public int getPoints(){
        return this.loyaltyPoints;
    }

    public void setPoints(int points){
        this.loyaltyPoints = points;
    }
}
