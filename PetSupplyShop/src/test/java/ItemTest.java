import static org.junit.Assert.*;

import shop.models.Brand;
import shop.models.Item;
import org.junit.Test;

public class ItemTest {

    @Test
    public void ConstructorTest(){
        // Given
        Item item = new Item("A1");
        String expected = "A1";

        // When
        String actual = item.getItemID();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setItemNameTest(){
        // Given
        Item item = new Item((String) null);
        String expected = "cat food";

        // When
        item.setItemName("cat food");
        String actual = item.getItemName();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setItemID(){
        // Given
        Item item = new Item((String) null);
        String expected = "A1";

        // When
        item.setItemID("A1");
        String actual = item.getItemID();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setItemPrice(){
        // Given
        Item item = new Item("A1");
        Double expected = 10.9;

        // When
        item.setPrice(10.9);
        Double actual = item.getPrice();

        // Then
        assertEquals(expected, actual, .001);
    }

    @Test
    public void setCategory(){
        // Given
        Item item = new Item("A1");
        String expected = "Food";

        // When
        item.setCategory("Food");
        String actual = item.getCategory();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setBrand(){
        // Given
        Item item = new Item("A1");
        String brandName = "Newmans Own Organics Dog Food";
        Brand expected = new Brand(100,brandName);

        // When
        item.setBrand(expected);

        // Then
        assertEquals(expected, item.getBrand());
    }

    @Test
    public void getReviewsTest(){
        Item item = new Item("A1");
        assertEquals(0,item.getReviews(),0.1);
    }

    @Test
    public void setReviewsTest(){
        Item item = new Item("A1");
        item.setReviews(20);
        assertEquals(20,item.getReviews(),0.1);
    }

    @Test
    public void getRatingTest(){
        Item item = new Item("A1");
        assertEquals(0.0,item.getRating(),0.1);
    }

    @Test
    public void setRatingTest(){
        Item item = new Item("A1");
        item.setRating(5.0);
        assertEquals(5.0,item.getRating(),0.1);
    }


    @Test
    public void getImageURLTest(){
        Item item = new Item("A1");
        assertEquals("",item.getImageURL());
    }

    @Test
    public void setImageURLTest(){
        Item item = new Item("A1");
        item.setImageURL("xxx.jpg");
        assertEquals("xxx.jpg",item.getImageURL());
    }

    @Test
    public void getAmazonURLTest(){
        Item item = new Item("A1");
        assertEquals("",item.getAmazonURL());
    }

    @Test
    public void setAmazonURLTest(){
        Item item = new Item("A1");
        item.setAmazonURL("xxx.com");
        assertEquals("xxx.com",item.getAmazonURL());
    }

    @Test
    public void parsePriceTest(){
        Item item = new Item("A1");
        String priceString = "$9.25";
        Double expected = 9.25;
        Double actual = item.parsePrice(priceString);
        assertEquals(expected,actual);
    }


}
