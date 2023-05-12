package Customer.CustomerCatalog;

public class Category {
    //- categoryName: string
    //- products : Product[]
    private String categoryName;
    private Product[] products;

    Category(String categoryName, Product[] products){
        this.categoryName = categoryName;
        this.products = products;
    }
    public Product getProduct(int id){
        return null;
    }
    
}
