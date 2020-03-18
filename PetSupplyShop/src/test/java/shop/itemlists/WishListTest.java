package shop.itemlists;

import org.junit.Assert;
import org.junit.Test;

public class WishListTest {

    @Test
    public void nullaryConstructorTest(){
        WishList l = new WishList();
        Assert.assertNull(l.getListName());
        Assert.assertNull(l.getId());
    }
    @Test
    public void constructorTest(){
        WishList l = new WishList(20,"myList");
        Assert.assertEquals(0,l.getListSize());
    }
    @Test
    public void getListName() {
        WishList l = new WishList(20,"myList");
        Assert.assertEquals("myList",l.getListName());
    }

    @Test
    public void setListName() {
        WishList l = new WishList(20, "myList");
        l.setListName("new new");
        Assert.assertEquals("new new", l.getListName());
    }

    @Test
    public void getId() {
        WishList l = new WishList(20,"myList");
        Assert.assertEquals(20, l.getId(), 0.1);
    }

    @Test
    public void setId() {
        WishList l = new WishList(20, "myList");
        l.setId(31);
        Assert.assertEquals(31, l.getId(),0.1);
    }
}