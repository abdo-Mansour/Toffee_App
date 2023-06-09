package Customer.CustomerCatalog;

import Customer.CustomerCart.Cart;
public class Product {
    
    private int productID;
    private String name;
    private Category category;
    private String itemDescription;
    private String brand;
    private float price;
    private boolean isLoose;
    private float discount;
    private String status;
    private Cart userCart;
    private float availableQuantity;


    public Product(int productID ,String name, String itemDescription, String brand, float price, boolean isLoose, float discount, String status , float availableQuantity){
        this.productID = productID;
        this.name = name;
        // this.category = category;
        this.itemDescription = itemDescription;
        this.brand = brand;
        this.price = price;
        this.isLoose = isLoose;
        this.discount = discount;
        this.status = status;
        // this.userCart = userCart;
        this.availableQuantity = availableQuantity;
    }


    public void addToCart(float amount){
        if(userCart == null){
            System.out.println("Please login first");
            return;
        }
        setAvailableQuantity(availableQuantity - (int)amount);
        if(this.isLoose){
            this.userCart.addLoose(this,amount);
        }
        else{
            this.userCart.addUnit(this,(int)amount);
        }
    }

    public int getID(){
        return this.productID;
    }

    public Category getCategory(){
        return this.category;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.itemDescription;
    }

    public String getBrand(){
        return this.brand;
    }

    public float getPrice(){
        return this.price;
    }

    public float getDiscount(){
        return this.discount;
    }

    public String getStatus(){
        return this.status;
    }

    //returns true if product is loose, false if product is units
    public boolean getType(){
        return this.isLoose;
    }

    public float getAvailableQuantity(){
        return this.availableQuantity;
    }

    public void setAvailableQuantity(float availableQuantity){
        this.availableQuantity = availableQuantity;
    }

    public void setCart(Cart cart){
        this.userCart = cart;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}
