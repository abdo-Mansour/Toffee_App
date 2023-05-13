package Customer.CustomerAuthentication;

import Customer.CustomerCart.Cart;
import Customer.CustomerOrder.OrderManager;

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

    // constructor
    public RegUser(int sessionID, int userID, String username, String userEmail, String userPass, String userAddress) {
        this.userID = userID;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPass;
        this.userAddress = userAddress;
        this.userStatus = "Active";
        this.userCart = new Cart(this);
        this.orderManager = new OrderManager(this);
        this.loyaltyPoints = 0;
    }


    public int getPoints() {
        return this.loyaltyPoints;
    }

    public void setPoints(int points) {
        this.loyaltyPoints = points;
    }

    // getters and setters
    public int getUserID() {
        return this.userID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public Cart getUserCart() {
        return this.userCart;
    }

    public OrderManager getOrderManager() {
        return this.orderManager;
    }
    public int getLoyaltyPoints() {
        return this.loyaltyPoints;
    }

}
