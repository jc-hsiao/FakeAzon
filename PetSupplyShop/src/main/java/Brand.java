import java.util.ArrayList;
import java.util.List;

public class Brand {

    private String brandName;
    private int id;
    private List<Item> brandList;

    public Brand(){};

    public Brand(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
        brandList = new ArrayList<>();
    }

    public void addItem(Item item){
        brandList.add(item);
    }

    public Item getItem(int itemID){
        for(Item i : brandList){
            if(i.getItemID() == itemID){
                return i;
            }
        }
        return null;
    }

    public void removeByID(int itemID){
        Item item = getItem(itemID);
        brandList.remove(item);
    }

    public boolean containsItem(int itemID){
        Item item = getItem(itemID);
        return brandList.contains(item);
    }

    public void clearBrandList(){
        brandList.clear();
    }

    public int getSize(){
        return brandList.size();
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

    public List<Item> getBrandList() {
        return this.brandList;
    }
}
