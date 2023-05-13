package Admin.AdminOrder;

import java.util.ArrayList;

public class OrderManager {
    
    private ArrayList<Order> orders;

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
    }
} 
