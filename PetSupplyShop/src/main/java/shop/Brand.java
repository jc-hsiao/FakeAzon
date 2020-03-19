package shop;
import shop.itemlists.ProductList;

public class Brand {

    private String brandName;
    private int id;
    private ProductList listOfProduct;

    public Brand(){};

    public Brand(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
        this.listOfProduct = new ProductList();
    }

    public void addItem(Item item){
        listOfProduct.addItem(item);
    }

    public Item getItem(String itemID){
        return listOfProduct.getItem(itemID);
    }

    public boolean removeItemByID(String itemID){
        if(listOfProduct.containItem(itemID)) {
            listOfProduct.removeItem(itemID);
            return true;
        }else{
            return false;
        }
    }

    public boolean containsItem(String itemID){
        return listOfProduct.containItem(itemID);
    }

    public void clearBrandList(){
        listOfProduct.clearItems();
    }

    public int getSize(){
        return listOfProduct.getListSize();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductList getBrandList() {
        return listOfProduct;
    }
}
