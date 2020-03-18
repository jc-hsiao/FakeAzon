package API;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import shop.API.AmazonItem;

public class AmazonItemTest {

    AmazonItem item;

    @Before
    public void setup(){
        item = new AmazonItem();
    }

    @Test
    public void setASINTest(){
        item.setASIN("B013WC0P2A");
        assertEquals("B013WC0P2A", item.getASIN());
    }

    @Test
    public void setTitleTest(){
        item.setTitle("Mouse");
        assertEquals("Mouse", item.getTitle());
    }

    @Test
    public void setPriceTest(){
        item.setPrice(10.99);
        assertEquals(10.99, item.getPrice(), .001);
    }

    @Test
    public void setListPriceTest(){
        item.setListPrice(10.99);
        assertEquals(10.99, item.getListPrice(), .001);
    }

    @Test
    public void setImageUrlTest(){
        String expected = "https://images-na.ssl-images-amazon.com/images/I/41PpNHdOKJL._SL160_.jpg";
        item.setImageUrl(expected);
        assertEquals(expected, item.getImageUrl());
    }

    @Test
    public void setDetailPageUrlTest(){
        String expected = "https://www.amazon.com/VicTsing-Wireless-Portable-Receiver-Adjustable/dp/B013WC0P2A";
        item.setDetailPageUrl(expected);
        assertEquals(expected, item.getDetailPageUrl());
    }

    @Test
    public void setRatingTest(){
        item.setRating(4.5);
        assertEquals(4.5, item.getRating(), .001);
    }

    @Test
    public void setTotalReviewsTest(){
        item.setTotalReviews(123);
        assertEquals(123, item.getTotalReviews());
    }

    @Test
    public void setPrimeEligible(){
        item.setPrimeEligible(true);
        assertTrue(item.isPrimeEligible());
    }

}
