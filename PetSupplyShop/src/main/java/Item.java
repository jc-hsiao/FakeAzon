public class Item {

    private String name;
    private Integer itemID;
    private Double price;
    private String category;
    private String brand;

    public Item(){}

    public Item(Integer id) {
        this.itemID = id;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        this.name = itemName;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double itemPrice) {
        this.price = itemPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
