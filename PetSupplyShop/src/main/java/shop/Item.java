package shop;

import shop.api.AmazonItem;

public class Item {

    private String name;
    private String itemID;
    private Double price;
    private String category;
    private Brand brand;

    private Integer reviews;
    private Double rating;
    private String imageURL;
    private String amazonURL;

    public Item(){}

    public Item(String id) {
        this(id,"",0.0,"",null,0,0.0,"","");
    }


    public Item(String id, String name, Double price, String category, Brand brand) {
        this(id,name,price,category,brand,0,0.0,"","");
    }


    public Item(String id, String name, Double price, String category, Brand brand, Integer reviews, Double rating, String imageURL, String amazonURL) {
        this.name = name;
        this.itemID = id;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.reviews = reviews;
        this.rating = rating;
        this.imageURL = imageURL;
        this.amazonURL = amazonURL;
    }

    public Item(AmazonItem amazonItem){
        itemID = amazonItem.getASIN();
        name = amazonItem.getTitle();
        rating = amazonItem.getRating();
        reviews = amazonItem.getTotalReviews();
        imageURL = amazonItem.getImageUrl();
        amazonURL = amazonItem.getDetailPageURL();
        category = amazonItem.getSubtitle();

        if(amazonItem.getPrice().equals(""))
            price = parsePrice(amazonItem.getListPrice());
        else
            price = parsePrice(amazonItem.getPrice());
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        this.name = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Double parsePrice(String str){
        Double price = 0.0;
        if(str.equals(""))
            return -1.0;
        else
            return Double.valueOf(str.substring(1));
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

    public Integer getReviews() {
        return reviews;
    }

    public Double getRating() {
        return rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAmazonURL() {
        return amazonURL;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAmazonURL(String amazonURL) {
        this.amazonURL = amazonURL;
    }

}
