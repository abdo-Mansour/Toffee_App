package Customer.CustomerCatalog;


import java.util.ArrayList;
public class MainCatalog {
    //- categories : Category[]
    private ArrayList<Category> categories;
    // + searchByName( productName: string): Product[]
    // + searchByBrand( productBrand: string): Product[]
    // + getAllProductsUnder( categoryName: string): Product[]
    // + returnAll(): Product[]
    public MainCatalog(){
        loadCatalog();
    }

    public void loadCatalog(){
        //TODO: implement by database
        //get all info from database
        //create categories
        //create products
        //add products to categories


    }

    public ArrayList<Product> searchByName(String productName){
        //TODO: implement by database
        return null;
    }

    public ArrayList<Product> searchByBrand(String productBrand){
        //TODO: implement by database
        return null;
    }

    public ArrayList<Product> getAllProductsUnder(String categoryName){
        Category category = null;
        for(Category cat : categories){
            if(cat.getName().equals(categoryName)){
                category = cat;
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
