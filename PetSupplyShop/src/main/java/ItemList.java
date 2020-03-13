import java.util.HashSet;
import java.util.Set;

public class ItemList {
    String listName;
    Set<Item> items;

    public ItemList(){
        listName = "";
        items = new HashSet<>();
    }

    public ItemList(String listName, Set<Item> items){
        this.listName = listName;
        this.items = items;
    }

    public Item getItem(){
        return null;
    }

    public boolean addItem(Item item){
        if(!items.contains(item)) {
            items.add(item);
            return true;
        }else
            return false;//add failed
    }

    public boolean removeItem(Item item){
        if(!items.contains(item)) {
            return false;
        }else{
            items.remove(item);
            return true;
        }
    }

    public void clearItems(){
        items.clear();
    }

    public int getListSize(){
        return items.size();
    }

    public String getName(){
        return listName;
    }

    public void setListName(String newListName){
        listName = newListName;
    }

}
