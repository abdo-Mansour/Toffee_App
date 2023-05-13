package Admin.AdminCatalog;

public class Product {
    
    private int productID;
    private String name;
    private String category;
    private String itemDescription;
    private String brand;
    private float price;
    private boolean isLoose;
    private float discount;
    private String status;
    private int availableQuantity;

    public Product(int productID, String name, String category, String itemDescription, String brand, float price, boolean isLoose, float discount, String status , int availableQuantity) {
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.itemDescription = itemDescription;
        this.brand = brand;
        this.price = price;
        this.isLoose = isLoose;
        this.discount = discount;
        this.status = status;
        this.availableQuantity = availableQuantity; 
    }

    public int getID() {
        return productID;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return itemDescription;
    }

    public String getBrand() {
        return brand;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public String getStatus() {
        return status;
    }

    public boolean getType() {
        return isLoose;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setID(int productID) {
        this.productID = productID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(boolean isLoose) {
        this.isLoose = isLoose;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }


}
