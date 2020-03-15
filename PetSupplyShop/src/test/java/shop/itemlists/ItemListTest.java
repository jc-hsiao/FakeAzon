package shop.itemlists;

import org.junit.Assert;
import org.junit.Test;

import shop.Item;
import shop.Brand;
import shop.itemlists.ItemList;
import shop.itemlists.WishList;

public class ItemListTest {

    Item dogFood = new Item(3, "Organic dog food", 20.0, "Dog", new Brand());
    Item birdToy = new Item(4, "Natural chewing log", 4.0, "Bird", new Brand());
    Item catTreat = new Item(5, "Healthy treat for cat", 15.0, "Cat", new Brand());

    @Test
    public void constructorTest() {
        ItemList l = new WishList(1, "list1");
        Assert.assertEquals(l.getListSize(), 0);
    }

    @Test
    public void getItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        Assert.assertEquals(dogFood, l.getItem(3));
    }

    @Test
    public void getItemFailTest() {
        ItemList l = new WishList(1, "list1");
        Assert.assertNull(l.getItem(3));
    }

    @Test
    public void removeItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        l.removeItem(3);
        Assert.assertEquals(0, l.getListSize());
        Assert.assertNull(l.getItem(3));
    }

    @Test
    public void addItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        Assert.assertEquals(1, l.getListSize());
        Assert.assertEquals(dogFood, l.getItem(3));
    }

    @Test
    public void containsTest() {
        ItemList l = new WishList(1, "list1");
        Item item1 = new Item();
        item1.setItemID(5);
        l.addItem(item1);
        Assert.assertTrue(l.containItem(5));
    }


    @Test
    public void clearItemTest() {
        ItemList l = new WishList(1, "list1");
        l.addItem(dogFood);
        l.addItem(birdToy);
        l.addItem(catTreat);
        l.clearItems();
        Assert.assertEquals(0, l.getListSize());
        Assert.assertNull(l.getItem(3));
        Assert.assertNull(l.getItem(4));
        Assert.assertNull(l.getItem(5));
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
