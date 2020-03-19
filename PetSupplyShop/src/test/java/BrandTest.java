import shop.Item;
import org.junit.Test;
import shop.Brand;
import shop.itemlists.ProductList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BrandTest {

    @Test
    public void constructorTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // Then
        assertEquals(b.getId(), 1);
        assertEquals(b.getBrandName(), "Petco");
        assertEquals(b.getSize(), 0 );
    }

    @Test
    public void nullaryConstructorTest(){
        // Given
        Brand b = new Brand();
        // Then
        assertNull(b.getBrandName());
    }

    @Test
    public void getItemTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item();
        item.setItemID("A1");
        b.addItem(item);
        // Then
        assertEquals(item, b.getItem("A1"));
    }

    @Test
    public void getNullItemTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // Then
        assertNull(b.getItem("A1"));
    }

    @Test
    public void addItemTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        // When
        b.addItem(item);
        // Then
        assertEquals(1, b.getSize());
        assertEquals(item, b.getItem("A1"));
    }

    @Test
    public void removeItemByIDTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        b.addItem(item);// When
        boolean remove = b.removeItemByID("A1");
        // Then
        assertEquals(0, b.getSize());
        assertNull(b.getItem("A1"));
        assertTrue(remove);
    }

    @Test
    public void removeItemByIDFailTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        assertFalse(b.removeItemByID("A1"));
    }

    @Test
    public void containsItemTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        b.addItem(item);
        // Then
        assertTrue(b.containsItem("A1"));
    }

    @Test
    public void clearBrandListTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        Item item1 = new Item("A2");
        Item item2 = new Item("A3");
        b.addItem(item);
        b.addItem(item1);
        b.addItem(item2);
        // When
        b.clearBrandList();
        // Then
        assertEquals(0, b.getSize());
        assertNull(b.getItem("A2"));
    }

    @Test
    public void sizeTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        Item item1 = new Item("A2");
        Item item2 = new Item("A3");
        // When
        b.addItem(item);
        b.addItem(item1);
        b.addItem(item2);
        // Then
        assertEquals(3, b.getSize());

    }

    @Test
    public void getBrandNameTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // Then
        assertEquals("Petco", b.getBrandName());
    }

    @Test
    public void setBrandNameTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // When
        b.setBrandName("Chewy");
        // Then
        assertEquals("Chewy", b.getBrandName());
    }

    @Test
    public void getIDTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // Then
        assertEquals(1, b.getId());
    }

    @Test
    public void setIDTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        // When
        b.setId(2);
        // Then
        assertEquals(2, b.getId());
    }

    @Test
    public void getBrandListTest(){
        // Given
        Brand b = new Brand(1, "Petco");
        Item item = new Item("A1");
        Item item1 = new Item("A2");
        Item item2 = new Item("A3");
        // When
        b.addItem(item);
        b.addItem(item1);
        b.addItem(item2);
        ProductList brandList= b.getBrandList();
        // Then
        assertEquals(brandList.getListSize(), 3);
    }

}
