package shop.itemlists;

public class WishList extends ItemList {

    private String listName;
    private Integer id;

    public WishList(){ }

    public WishList(int id,String listName){
        super();
        this.id = id;
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
