package Customer.CustomerCart;

import Customer.CustomerCatalog.Product;

public class LooseItem extends CartItem {
    private float weight;

    LooseItem(Product product, int itemID, float weight){
        super(product, itemID);
        this.weight = weight;
    }

    public float getWeight(){
        return this.weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }
}
