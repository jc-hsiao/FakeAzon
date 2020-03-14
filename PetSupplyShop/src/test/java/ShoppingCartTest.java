import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ShoppingCartTest {

    Item item1 = new Item(1);
    Item item2 = new Item(2);
    Item item3 = new Item(3);



    @Test
    public void addTest(){

        ShoppingCart cart = new ShoppingCart();
        Integer expectedSize = 0;
        Assert.assertEquals(expectedSize ,cart.getNumOfItems());

        boolean addItem1 = cart.add(item1 ,1);
        boolean addItem2 = cart.add(item2 ,1);
        boolean addItem3 = cart.add(item3 ,1);

        Integer expectedSize2 = 3;
        Assert.assertEquals(expectedSize2 , cart.getNumOfItems());
    }
    @Test
    public void addSameItemTest(){
        ShoppingCart cart = new ShoppingCart();

        boolean addItem1 = cart.add(item1 ,1);
        boolean addItem2 = cart.add(item1 ,10);
        boolean addItem3 = cart.add(item1 ,10);

        Integer expectedSize2 = 1;
        Assert.assertEquals(expectedSize2 , cart.getNumOfItems());
    }
    @Test
    public void addZeroOrLessTest(){
        ShoppingCart cart = new ShoppingCart();
        boolean addItem1 = cart.add(item1 ,0);
        boolean addItem2 = cart.add(item1 ,-12);

        Integer expectedSize2 = 0;
        Assert.assertEquals(expectedSize2 , cart.getNumOfItems());
        Assert.assertFalse(addItem1);
        Assert.assertFalse(addItem2);
    }

    @Test
    public void removeTest(){
        ShoppingCart cart = new ShoppingCart();

        boolean addItem1 = cart.add(item1 ,1);
        boolean addItem2 = cart.add(item2 ,1);
        boolean addItem3 = cart.add(item3 ,1);

        Integer expectedSize1 = 3;
        Assert.assertEquals(expectedSize1 , cart.getNumOfItems());

        boolean rmv1 = cart.remove(item1);
        Integer expectedSize2 = 2;
        Assert.assertEquals(expectedSize2 , cart.getNumOfItems());
        Assert.assertTrue(rmv1);
    }
    @Test
    public void removeNonExistentTest(){
        ShoppingCart cart = new ShoppingCart();
        boolean addItem1 = cart.add(item1 ,1);

        Item notInCart = new Item(7);
        boolean notInCartResult = cart.remove(notInCart);

        Integer expectedSize1 = 1;
        Assert.assertEquals(expectedSize1 , cart.getNumOfItems());
        Assert.assertFalse(notInCartResult);
    }

    @Test
    public void replaceItemAmountTest(){

        ShoppingCart cart = new ShoppingCart();

        boolean addItem1 = cart.add(item1 ,1);
        Integer expectedAmount1 = 1;
        Assert.assertEquals(expectedAmount1 , cart.getItemAmount(item1));

        cart.replaceItemAmount(item1 , 10);
        Integer expectedAmount2 = 10;
        Assert.assertEquals(expectedAmount2 , cart.getItemAmount(item1));
    }
    @Test
    public void replaceNonItemAmountTest(){

        ShoppingCart cart = new ShoppingCart();

        boolean addItem1 = cart.add(item1 ,1);
        Integer expectedAmount1 = 1;
        Assert.assertEquals(expectedAmount1 , cart.getItemAmount(item1));

        Item notInCart = new Item(7);
        boolean replaceBool = cart.replaceItemAmount(notInCart , 10);

        Integer expectedAmount2 = 1;
        Assert.assertEquals(expectedAmount2 , cart.getItemAmount(item1));
        Assert.assertFalse(replaceBool);
    }
    @Test
    public void increaseItemAmountTest(){

        ShoppingCart cart = new ShoppingCart();
        boolean addItem1 = cart.add(item1 ,1);

        boolean increase20 = cart.increaseItemAmount(item1 , 20);

        Integer expectedAmount2 = 21;
        Assert.assertEquals(expectedAmount2 , cart.getItemAmount(item1));
        Assert.assertTrue(increase20);
    }
    @Test
    public void  increaseNonItemAmountTest(){
        ShoppingCart cart = new ShoppingCart();
        boolean addItem1 = cart.add(item1 ,1);

        Item nonexixtent =  new Item(7);
        boolean increase20 = cart.increaseItemAmount(nonexixtent , 20);

        Integer expectedAmount2 = 1;
        Assert.assertEquals(expectedAmount2 , cart.getItemAmount(item1));
        Assert.assertFalse(increase20);
    }

    // NOT FINISHED

    @Test
    public void getCartValueTest(){

        ShoppingCart cart = new ShoppingCart();

        Double expectedValue1 = 0.00;//Pre-Items Value
        Double actualValue1 =  cart.getCartValue();

        Assert.assertEquals(expectedValue1 , actualValue1);

        Item item1 = new Item(0); // Waiting on items to be created with price field
        Item item2 = new Item(0); // Waiting on items to be created with price field

        Double expectedValue2 = 0.00;//Post-Items Value
        Double actualValue2 =  cart.getCartValue();

        Assert.assertEquals(expectedValue2 , actualValue2);
    }

    @Test
    public void getItemsTest(){

        ShoppingCart cart = new ShoppingCart();
        HashMap<Item , Integer> emptyMap = new HashMap<Item, Integer>();
        Assert.assertEquals(emptyMap , cart.getItems());

        boolean addItem1 = cart.add(item1 ,1);
        boolean addItem2 = cart.add(item2 ,1);
        boolean addItem3 = cart.add(item3 ,1);

        Assert.assertTrue(cart.getItems().containsKey(item1));
        Assert.assertTrue(cart.getItems().containsKey(item2));
        Assert.assertTrue(cart.getItems().containsKey(item3));
    }
}
