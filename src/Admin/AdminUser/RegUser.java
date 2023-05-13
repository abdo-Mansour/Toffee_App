package Admin.AdminUser;

public class RegUser {
    private int userID;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String userStatus;
    private int loyaltyPoints;

    // constructor
    public RegUser(int sessionID, int userID, String username, String userEmail, String userPass, String userAddress) {
        this.userID = userID;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPass;
        this.userAddress = userAddress;
        this.userStatus = "Active";
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

}
