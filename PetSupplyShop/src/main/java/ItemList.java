import java.util.HashSet;
import java.util.Set;

public class ItemList {
    private int id;
    private String listName;
    private Set<Item> items;

    public ItemList(){}

    public ItemList(int id, String listName){
        this.id = id;
        this.listName = listName;
        items = new HashSet<>();
    }

    public Item getItem(int itemId){
        for (Item i:items) {
            if(i.getItemID()==itemId)
                return i;
        }
        return null;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(int itemId){
        Item item = getItem(itemId);
        items.remove(item);
    }

    public boolean containItem(int itemId){
        Item item = getItem(itemId);
        return items.contains(item);
    }

    public void clearItems(){
        items.clear();
    }

    public int getListSize(){
        return items.size();
    }

    public String getListName(){
        return listName;
    }

    public void setListName(String newListName){
        listName = newListName;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
