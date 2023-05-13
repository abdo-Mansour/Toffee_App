package Customer.CustomerCart;

import Customer.CustomerCatalog.Product;

public class UnitItem extends CartItem {
    private int units;

    UnitItem(Product product, int itemID, int units){
        super(product, itemID);
        this.units = units;
    }

    public int getQuantity(){
        return this.units;
    }
    
    public void setQuantity(int quantity){
        this.units = quantity;
    }
}
