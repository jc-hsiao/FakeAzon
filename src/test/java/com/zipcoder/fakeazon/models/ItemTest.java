package com.zipcoder.fakeazon.models;
import org.junit.*;


public class ItemTest {

    Item item = new Item();
    int id = 1;
    String ASIN = "ASDFG";
    String title = "iphone8 protective screen";
    String price = "$5.25";
    String listedPrice = "$3.25";
    String imageUrl = "pic.jpg";
    String detailPageURL = "amazon.com/some/page";
    double rating = 4.8;
    int totalReviews = 1250;
    String subtitle = "some company";


    @Before
    public void setUp(){
        item.setId(id);
        item.setASIN(ASIN);
        item.setTitle(title);
        item.setPrice(price);
        item.setListPrice(listedPrice);
        item.setImageUrl(imageUrl);
        item.setDetailPageURL(detailPageURL);
        item.setRating(rating);
        item.setTotalReviews(totalReviews);
        item.setSubtitle(subtitle);
    }


    @Test
    public void getId() { Assert.assertEquals(id, item.getId()); }

    @Test
    public void getASIN() { Assert.assertEquals(ASIN, item.getASIN()); }

    @Test
    public void getTitle() { Assert.assertEquals(title, item.getTitle()); }

    @Test
    public void getPrice() { Assert.assertEquals(price, item.getPrice()); }

    @Test
    public void getListPrice() {
        Assert.assertEquals(listedPrice, item.getListPrice());
    }

    @Test
    public void getImageUrl() {
        Assert.assertEquals(imageUrl, item.getImageUrl());
    }

    @Test
    public void getDetailPageURL() {
        Assert.assertEquals(detailPageURL, item.getDetailPageURL());
    }


    @Test
    public void getRating() {
        Assert.assertEquals(rating, item.getRating(),0.1);
    }

    @Test
    public void getTotalReviews() {
        Assert.assertEquals(totalReviews, item.getTotalReviews());
    }

    @Test
    public void getSubtitle() {
        Assert.assertEquals(subtitle, item.getSubtitle());
    }


}