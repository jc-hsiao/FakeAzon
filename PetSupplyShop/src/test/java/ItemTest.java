import static org.junit.Assert.*;

import shop.Brand;
import shop.Item;
import org.junit.Test;

public class ItemTest {

    @Test
    public void ConstructorTest(){
        // Given
        Item item = new Item(1);
        Integer expected = 1;

        // When
        Integer actual = item.getItemID();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setItemNameTest(){
        // Given
        Item item = new Item(null);
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
        Item item = new Item(null);
        Integer expected = 1;

        // When
        item.setItemID(1);
        Integer actual = item.getItemID();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void setItemPrice(){
        // Given
        Item item = new Item(1);
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
        Item item = new Item(1);
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
        Item item = new Item(1);
        String brandName = "Newmans Own Organics Dog Food";
        Brand expected = new Brand(100,brandName);

        // When
        item.setBrand(expected);

        // Then
        assertEquals(expected, item.getBrand());
    }


}
