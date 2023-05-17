package Admin.AdminOrder;

import Database.Db;
import java.util.ArrayList;

public class OrderManager {
    
    private ArrayList<Order> orders;
    private Db db = new Db();

    public OrderManager() {
        orders = new ArrayList<Order>();
    }

    public Order getOrder(int orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void closeOrder(int orderID) {
        Order order = getOrder(orderID);
        order.setOrderStatus("Closed");

        //TODO: update database
        db.connectServer();
        db.executeQuery("UPDATE `Orders` SET `orderStatus` = 'Closed' WHERE `order`.`orderID` = " + orderID);
        db.closeConnection();
    }
} 
