package Customer.CustomerPayment;

import Customer.CustomerOrder.Date;
import Customer.CustomerOrder.Order;
import Database.Db;
public abstract class Payment {
    
    protected int paymentID;
    protected Date paymentDate;
    protected float paymentAmount;
    protected String paymentStatus;
    protected int orderID;
    protected Order order;
    protected Db db = new Db();
    

    public Payment(Order order){
        this.order = order;
        this.orderID = order.getID();
        this.paymentStatus = "Pending";
        this.paymentAmount = order.getTotal();
        this.paymentDate = order.getDate();
    }

    public void processPayment(){};

    public void cancelPayment(){
        this.paymentStatus = "Cancelled";
    }

    public void viewPaymentDetails(){
        System.out.println("Payment ID: " + this.paymentID);
        System.out.println("Payment Date: " + this.paymentDate);
        System.out.println("Payment Amount: " + this.paymentAmount);
        System.out.println("Payment Status: " + this.paymentStatus);
        System.out.println("Order ID: " + this.orderID);
    }

    public void saveOrder(){
        db.connectServer();
        db.addOrder(this.order);
        db.closeConnection();
        //TODO : implement with database ?
    }

    
}
