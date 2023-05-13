package Customer.CustomerCart;

import java.util.ArrayList;
import Customer.CustomerCatalog.Product;
import Customer.CustomerOrder.OrderManager;
import Customer.CustomerAuthentication.RegUser;
public class Cart {
//     - unitItems: UnitItems[]
// - looseItems: LooseItems[]
// - total: float
// - user : RegUser

    private ArrayList<UnitItem> unitItems;
    private ArrayList<LooseItem> looseItems;
    private static int cartSize = 0;
    private float total;
    private RegUser user;

    public Cart(RegUser user){
        this.unitItems = new ArrayList<UnitItem>();
        this.looseItems = new ArrayList<LooseItem>();
        this.total = 0;
        this.user = user;
    }


    public void checkOut(){
        OrderManager orderManager = this.user.getOrderManager();
        orderManager.placeOrder(this);
    }

    public void addLoose(Product product, float amount){
        
        LooseItem looseItem = new LooseItem(product,cartSize++,amount);
        this.looseItems.add(looseItem);
        this.total += product.getPrice() * amount;
        
    }

    public void addUnit(Product product, int amount){
        
        UnitItem unitItem = new UnitItem(product,cartSize++,amount);
        this.unitItems.add(unitItem);
        this.total += product.getPrice() * amount;

    }

    public void printCart(){
        System.out.println("Unit Items:");
        for(UnitItem unitItem : this.unitItems){
            Product product = unitItem.getProduct();
            System.out.println(" name: " + product.getName() + " price: " + product.getPrice() + " amount: " + unitItem.getQuantity());
        }
        System.out.println("Loose Items:");
        for(LooseItem looseItem : this.looseItems){
            Product product = looseItem.getProduct();
            System.out.println(" name: " + product.getName() + " price: " + product.getPrice() + " amount: " + looseItem.getWeight());
        }
    }

    public float getTotal(){
        return this.total;
    }
}
