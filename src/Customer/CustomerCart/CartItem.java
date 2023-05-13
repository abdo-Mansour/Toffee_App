package Customer.CustomerCart;

import Customer.CustomerCatalog.Product;

public abstract class CartItem {
    protected int productID;
    protected Product product;
    protected int itemID;

    CartItem(Product product, int itemID){
        this.product = product;
        this.productID = product.getID();
        this.itemID = itemID;
    }

    public Product getProduct(){
        return this.product;
    }
}
