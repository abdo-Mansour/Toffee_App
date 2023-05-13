package Customer.CustomerOrder;

import Customer.CustomerCart.Cart;
import Customer.CustomerAuthentication.RegUser;

import java.util.ArrayList;
public class OrderManager {
    
    private ArrayList<Order> orders;
    private RegUser user;
    private static int currentOrderID = 0;
    

    public OrderManager(RegUser user){
        this.user = user;
        this.orders = new ArrayList<Order>();
        getData();
    }

    //save order to database after it has been placed
    private void sendData(Order currOrder){
        //TODO: send data to database
    }

    //get all order data from database
    private void getData(){
        //TODO: get data from database
    }

    public Order getOrder(int id){
        for(Order ord : orders){
            if(ord.getID() == id){
                return ord;
            }
        }
        return null;
    }

    public void placeOrder(Cart cart){
        //TODO: make sure to give the user the option to choose the address

        Order newOrder = new Order(this.user, generateOrderID() , cart, this.user.getUserAddress());
        this.orders.add(newOrder);
        sendData(newOrder);
    }

    public static int generateOrderID(){
        return currentOrderID++;
    }

    public ArrayList<Order> viewAllOrders(){
        return this.orders;
    }

    public int getCurrentOrderID(){
        return currentOrderID;
    }

    public void reorder(int oldOrderID){
        placeOrder(getOrder(oldOrderID).getCart());
    }

}
