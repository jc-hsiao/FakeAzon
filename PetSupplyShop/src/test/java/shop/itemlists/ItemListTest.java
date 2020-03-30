package shop.itemlists;

import org.junit.Assert;
import org.junit.Test;

import shop.models.Item;
import shop.models.Brand;

public class ItemListTest {

    Item dogFood = new Item("B3", "Organic dog food", 20.0, "Dog", new Brand());
    Item birdToy = new Item("B4", "Natural chewing log", 4.0, "Bird", new Brand());
    Item catTreat = new Item("B5", "Healthy treat for cat", 15.0, "Cat", new Brand());

    @Test
    public void constructorTest() {
        ItemList l = new WishList(1, "list1");
        Assert.assertEquals(l.getListSize(), 0);
    }

    @Test
    public void getItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        Assert.assertEquals(dogFood, l.getItem("B3"));
    }

    @Test
    public void getItemFailTest() {
        ItemList l = new WishList(1, "list1");
        Assert.assertNull(l.getItem("B3"));
    }

    @Test
    public void removeItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        l.removeItem("B3");
        Assert.assertEquals(0, l.getListSize());
        Assert.assertNull(l.getItem("B3"));
    }

    @Test
    public void addItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        Assert.assertEquals(1, l.getListSize());
        Assert.assertEquals(dogFood, l.getItem("B3"));
    }

    @Test
    public void containsTest() {
        ItemList l = new WishList(1, "list1");
        Item item1 = new Item();
        item1.setItemID("B5");
        l.addItem(item1);
        Assert.assertTrue(l.containItem("B5"));
    }


    @Test
    public void clearItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        l.addItem(birdToy);
        l.addItem(catTreat);
        l.clearItems();
        Assert.assertEquals(0, l.getListSize());
        Assert.assertNull(l.getItem("B3"));
        Assert.assertNull(l.getItem("B4"));
        Assert.assertNull(l.getItem("B5"));
    }

    @Test
    public void getListSizeTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        l.addItem(birdToy);
        l.addItem(catTreat);
        Assert.assertEquals(3, l.getListSize());
    }
}
