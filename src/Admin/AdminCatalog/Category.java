package Admin.AdminCatalog;

import java.util.ArrayList;

public class Category {
    /*
     * - categoryName: string
- products : Product[]
    */
    private int categoryID;
    private String categoryName;
    private ArrayList<Product> products;

    /*
     * + getProduct(id : int): Product
    */

    public Category(int categoryID, String categoryName , ArrayList<Product> products) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.products = new ArrayList<Product>(products);
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getID() == id) {
                return product;
            }
        }
        return null;
    }

    public String getName() {
        return categoryName;
    }

    public int getID() {
        return categoryID;
    }

}
