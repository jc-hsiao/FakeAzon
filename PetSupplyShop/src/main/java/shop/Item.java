package shop;

public class Item {

    private String name;
    private Integer itemID;
    private Double price;
    private String category;
    private Brand brand;

    public Item(){}

    public Item(Integer id) {
        this(id,"",0.0,"",null);
    }

    public Item(Integer id, String name, Double price, String category, Brand brand) {
        this.name = name;
        this.itemID = id;
        this.price = price;
        this.category = category;
        this.brand = brand;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


}
