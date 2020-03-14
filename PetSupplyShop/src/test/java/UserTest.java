import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    Item dogFood = new Item(3,"Organic dog food",20.0,"Dog",new Brand());
    Item birdToy = new Item(4,"Natural chewing log",4.0,"Bird",new Brand());
    Item catTreat = new Item(5,"Healthy treat for cat",15.0,"Cat",new Brand());

    @Test
    public void constructorTest(){
        User u = new User(1,"Leila","Hsiao");
        Assert.assertEquals(1,u.getId());
        Assert.assertEquals("Leila",u.getFirstName());
        Assert.assertEquals("Hsiao",u.getLastName());
        Assert.assertEquals(0,u.getShoppingCart().getNumOfItems(),0.1);
        Assert.assertEquals(0,u.getWishLists().size());
    }

    @Test
    public void setFirstNameTest(){
        User u = new User(1,"Leila","Hsiao");
        u.setFirstName("Lia");
        u.setLastName("Chen");
        Assert.assertEquals("Lia",u.getFirstName());
        Assert.assertEquals("Chen",u.getLastName());
    }

    @Test
    public void setShoppingCartTest(){
        User u = new User(1,"Leila","Hsiao");
        ShoppingCart sc = new ShoppingCart();
        sc.add(dogFood,2);
        u.setShoppingCart(sc);
        Assert.assertEquals(1,u.getNumOfItemInCart());
    }

    @Test
    public void addItemToCartTest(){
        User u = new User(1,"Leila","Hsiao");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean add2 = u.addItemToCart(catTreat,2);
        Assert.assertEquals(2,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertTrue(add2);
    }

    @Test
    public void addItemToCartFailTest(){
        User u = new User(1,"Leila","Hsiao");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean add2 = u.addItemToCart(birdToy,-99);
        Assert.assertEquals(1,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertFalse(add2);
    }

    @Test
    public void removeItemFromCartTest(){
        User u = new User(1,"Leila","Hsiao");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean remove = u.removeItemFromCart(birdToy);
        Assert.assertEquals(0,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertTrue(remove);
    }

    @Test
    public void removeItemFromCartFailTest(){
        User u = new User(1,"Leila","Hsiao");
        Assert.assertEquals(0,u.getNumOfItemInCart());
        boolean remove = u.removeItemFromCart(birdToy);
        Assert.assertFalse(remove);
    }

    @Test
    public void changeItemQuantityTest(){
        User u = new User(1,"Leila","Hsiao");
        u.addItemToCart(birdToy, 5);
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,8);
        Assert.assertTrue(change);
        Assert.assertEquals(8,u.getItemQuantity(birdToy));
    }

    @Test
    public void changeItemQuantityTest2(){
        User u = new User(1,"Leila","Hsiao");
        u.addItemToCart(birdToy, 5);
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,0);
        Assert.assertTrue(change);
        Assert.assertFalse(u.checkIfCartHas(birdToy));
        Assert.assertEquals(1,u.getNumOfItemInCart());
    }

    @Test
    public void changeItemQuantityFailTest(){
        User u = new User(1,"Leila","Hsiao");
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,8);
        Assert.assertFalse(change);
        Assert.assertEquals(0,u.getItemQuantity(birdToy));
    }

    @Test
    public void changeItemQuantityFailTest2(){
        User u = new User(1,"Leila","Hsiao");
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(catTreat,-1);
        Assert.assertFalse(change);
    }




}
