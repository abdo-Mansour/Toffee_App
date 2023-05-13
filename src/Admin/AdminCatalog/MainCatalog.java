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
        //TODO: database
        return null;
    }

    public ArrayList<Product> searchByBrand(String productBrand) {
        //TODO: database
        return null;
    }

    public ArrayList<Product> getAllProductsUnder(String categoryName) {
        //TODO: database
        
        return null;
    }

    public ArrayList<Product> returnAll() {
        //TODO: database
        
        return null;
    }

    public void createCategory(Category category) {
        //TODO: database

    }

    public void addProduct(Product product) {
        //TODO: database
        
    }


}
