package Customer.CustomerOrder;

import java.time.LocalDateTime; 

import Customer.CustomerCart.Cart;
import Customer.CustomerPayment.CashPay;
import Customer.CustomerAuthentication.RegUser;
public class Order {
    /*
    * - orderID: int
    - user: RegUser
    - userID: int
    - orderStatus: string
    - orderDate: Date
    - cart: Cart
    - totalPrice : float
    - orderDestination: String
    */
    private int orderID;
    private RegUser user;
    private String orderStatus;
    private Date orderDate;
    private Cart cart;
    private float totalPrice;
    private String orderDestination;

    
    public Order(RegUser user , int id, Cart cart, String address) {
        this.user = user;
        this.orderID = id;
        this.cart = cart;
        this.orderDestination = address;
        this.orderStatus = "Pending";
        
        LocalDateTime now = LocalDateTime.now(); 
        this.orderDate = new Date(now.getDayOfMonth(),now.getMonthValue(),now.getYear());
        
        if(cart != null){
            this.totalPrice = cart.getTotal();
        }else{
            this.totalPrice = 0;
        }
    }

    public boolean payOrder(String method,int phoneNum){
        //TODO: implement payment method
        CashPay cash = new CashPay(this,phoneNum);
        cash.processPayment();
        return true;
    }

    public RegUser getUser(){
        return this.user;
    }

    public int getID(){
        return this.orderID;
    }

    public String getStatus(){
        return this.orderStatus;
    }

    public void setStatus(String stat){
        this.orderStatus = stat;
    }

    public Date getDate(){
        return this.orderDate;
    }

    public Cart getCart(){
        return this.cart;
    }

    public float getTotal(){
        return this.totalPrice;
    }

    public void setTotal(float total){
        this.totalPrice = total;
    }

    public String getDestination(){
        return this.orderDestination;
    }

    public void setDestination(String add){
        this.orderDestination = add;
    }

    public void printOrder(){
        System.out.println("Order ID: " + this.orderID);
        System.out.println("Order Date: " + this.orderDate.getDay() + "/" + this.orderDate.getMonth() + "/" + this.orderDate.getYear());
        System.out.println("Order Status: " + this.orderStatus);
        System.out.println("Order Destination: " + this.orderDestination);
        System.out.println("Order Total: " + this.totalPrice);
        System.out.println("Order Cart: ");
        this.cart.printCart();
    }

    //testing
    // public static void main(String[] args) {
    //     RegUser user = null;
    //     Cart cart = null;
    //     Order order = new Order(user,1,cart,"my address blah blah");
    //     order.printOrder();
    // }
}
