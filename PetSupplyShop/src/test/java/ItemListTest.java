import org.junit.Assert;
import org.junit.Test;

public class ItemListTest {

    @Test
    public void constructorTest(){
        ItemList l = new ItemList(1,"list1");
        Assert.assertEquals(l.getId(),1);
        Assert.assertEquals(l.getListName(),"list1");
        Assert.assertEquals(l.getListSize(),0);
    }

    @Test
    public void getItemTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        item1.setItemID(5);
        l.addItem(item1);
        Assert.assertEquals(item1,l.getItem(5));
    }

    @Test
    public void getItemFailTest(){
        ItemList l = new ItemList(1,"list1");
        Assert.assertNull(l.getItem(5));
    }

    @Test
    public void removeItemTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        item1.setItemID(5);
        l.addItem(item1);
        l.removeItem(5);
        Assert.assertEquals(0,l.getListSize());
        Assert.assertNull(l.getItem(5));
    }

    @Test
    public void addItemTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        item1.setItemID(5);
        l.addItem(item1);
        Assert.assertEquals(1,l.getListSize());
        Assert.assertEquals(item1,l.getItem(5));
    }

    @Test
    public void containsTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        item1.setItemID(5);
        l.addItem(item1);
        Assert.assertTrue(l.containItem(5));
    }


    @Test
    public void clearItemTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setItemID(111);
        item2.setItemID(222);
        item2.setItemID(333);
        l.addItem(item1);
        l.addItem(item2);
        l.addItem(item3);
        l.clearItems();
        Assert.assertEquals(0,l.getListSize());
        Assert.assertNull(l.getItem(111));
        Assert.assertNull(l.getItem(222));
        Assert.assertNull(l.getItem(333));
    }

    @Test
    public void getListSizeTest(){
        ItemList l = new ItemList(1,"list1");
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setItemID(111);
        item2.setItemID(222);
        item2.setItemID(333);
        l.addItem(item1);
        l.addItem(item2);
        l.addItem(item3);
        Assert.assertEquals(3,l.getListSize());
    }

    @Test
    public void getListNameTest(){
        ItemList l = new ItemList(1,"myList");
        Assert.assertEquals("myList",l.getListName());
    }

    @Test
    public void getIdTest(){
        ItemList l = new ItemList(1,"myList");
        Assert.assertEquals(1,l.getId());
    }

    @Test
    public void setIdTest(){
        ItemList l = new ItemList(1,"myList");
        l.setId(555);
        Assert.assertEquals(555,l.getId());
    }

}
