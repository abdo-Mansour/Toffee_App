package Admin.AdminCatalog;

import java.util.ArrayList;

public class MainCatalog {
    
    private ArrayList<Category> categories;
    private LoyaltyPointScheme loyaltyScheme;
    private float rate;


    public MainCatalog() {
        this.categories = new ArrayList<Category>();
        this.loyaltyScheme = new LoyaltyPointScheme(rate);
    }

    public ArrayList<Product> searchByName(String productName) {
        return null;
    }

    public ArrayList<Product> searchByBrand(String productBrand) {
        return null;
    }

    public ArrayList<Product> getAllProductsUnder(String categoryName) {
        return null;
    }

    public ArrayList<Product> returnAll() {
        return null;
    }

    public void createCategory() {
    
    }

    public void addProduct() {
    
    }

    public void editProductInfo() {
    
    }

    public void removeProduct(int id) {
    
    }

}
