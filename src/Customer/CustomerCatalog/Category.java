package Customer.CustomerCatalog;

import java.util.ArrayList;

public class Category {
    
    private int categoryID;
    private String categoryName;
    private ArrayList<Product> products;

    Category(int id ,String categoryName, ArrayList<Product> products){
        this.categoryID = id;
        this.categoryName = categoryName;
        this.products = new ArrayList<Product>(products);
    }

    public ArrayList<Product> getProducts(){
        return this.products;
    }
    
    public Product getProduct(int id){
        for(Product product : products){
            if(product.getID() == id){
                return product;
            }
        }
        return null;
    }

    public String getName(){
        return this.categoryName;
    }
    
}
