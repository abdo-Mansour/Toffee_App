package Admin.AdminOrder;

import java.util.ArrayList;
public class Order {
   
    
    private int orderID;
    private ArrayList<Integer> productsID;
    private int userID;
    private Date orderDate;
    private String orderStatus;
    private float totalPrice;
    private String orderDestination;

    public Order(int orderID, ArrayList<Integer> productsID, int userID, Date orderDate, float totalPrice, String orderDestination, String orderStatus) {
        this.orderID = orderID;
        this.productsID = productsID;
        this.userID = userID;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderDestination = orderDestination;
    }

    public int getOrderID() {
        return orderID;
    }

    public ArrayList<Integer> getProductsID() {
        return productsID;
    }

    public int getUserID() {
        return userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDestination() {
        return orderDestination;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus=orderStatus;
    }

}
