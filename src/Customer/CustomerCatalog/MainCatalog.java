package Customer.CustomerCatalog;

import Database.Db;
import java.util.ArrayList;
public class MainCatalog {
    //- categories : Category[]
    private ArrayList<Category> categories;
    private Db db = new Db();
    public MainCatalog(){
        loadCatalog();
    }

    public void loadCatalog(){
        System.out.println("Loading catalog");
        db.connectServer();
        categories = db.getCategories();
        db.closeConnection();
    }

    public ArrayList<Product> searchByName(String productName){
        db.connectServer();
        ArrayList<Product> products = db.getProductWithName(productName);
        db.closeConnection();
        return products;
    }

    public ArrayList<Product> searchByBrand(String productBrand){
        db.connectServer();
        ArrayList<Product> products = db.getProductWithBrand(productBrand);
        db.closeConnection();
        return products;
    }

    public ArrayList<Product> getAllProductsUnder(String categoryName){
        Category category = null;
        for(Category cat : categories){
            if(cat.getName().equals(categoryName)){
                category = cat;
                break;
            }
        }
        if(category == null){
            return null;
        }
        return category.getProducts();
    }

    public ArrayList<Product> returnAll(){

        ArrayList<Product> allProducts = new ArrayList<Product>();
        
        for(Category category : categories){
            for(Product product : category.getProducts()){
                allProducts.add(product);
            }
        }
        return allProducts;

    }


}
