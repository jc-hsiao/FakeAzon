import shop.Item;
import org.junit.Assert;
import org.junit.Test;
import shop.Brand;
import shop.ShoppingCart;
import shop.User;

public class UserTest {

    Item dogFood = new Item("B3","Organic dog food",20.0,"Dog",new Brand());
    Item birdToy = new Item("B4","Natural chewing log",4.0,"Bird",new Brand());
    Item catTreat = new Item("B5","Healthy treat for cat",15.0,"Cat",new Brand());


    @Test
    public void nullaryConstructorTest(){
        User u = new User();
        Assert.assertNull(u.getUsername());
        Assert.assertNull(u.getFirstName());
        Assert.assertNull(u.getLastName());
        Assert.assertNull(u.getShoppingCart());
        Assert.assertNull(u.getWishLists());
        Assert.assertNull(u.getOrderHistory());
    }
    @Test
    public void constructorTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        Assert.assertEquals("leila12",u.getUsername());
        Assert.assertEquals("Leila",u.getFirstName());
        Assert.assertEquals("Hsiao",u.getLastName());
        Assert.assertEquals("password", u.getPassword());
        Assert.assertEquals("leila@gmail.com", u.getEmail());
        Assert.assertEquals(0,u.getShoppingCart().getNumOfItems(),0.1);
        Assert.assertEquals(0,u.getWishLists().size());
    }

    @Test
    public void setUserNameTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.setUsername("leila_hsiao");
        Assert.assertEquals("leila_hsiao", u.getUsername());
    }

    @Test
    public void setPasswordTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.setPassword("apples123");
        Assert.assertEquals("apples123", u.getPassword());
    }

    @Test
    public void setEmailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.setEmail("leila_hsiao@gmail.com");
        Assert.assertEquals("leila_hsiao@gmail.com", u.getEmail());
    }


    @Test
    public void setFirstNameTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.setFirstName("Lia");
        u.setLastName("Chen");
        Assert.assertEquals("Lia",u.getFirstName());
        Assert.assertEquals("Chen",u.getLastName());
    }

    @Test
    public void setShoppingCartTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        ShoppingCart sc = new ShoppingCart();
        sc.add(dogFood,2);
        u.setShoppingCart(sc);
        Assert.assertEquals(1,u.getNumOfItemInCart());
    }

    @Test
    public void addItemToCartTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean add2 = u.addItemToCart(catTreat,2);
        Assert.assertEquals(2,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertTrue(add2);
    }

    @Test
    public void addItemToCartFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean add2 = u.addItemToCart(birdToy,-99);
        Assert.assertEquals(1,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertFalse(add2);
    }

    @Test
    public void removeItemFromCartTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        boolean add = u.addItemToCart(birdToy, 5);
        boolean remove = u.removeItemFromCart(birdToy);
        Assert.assertEquals(0,u.getNumOfItemInCart());
        Assert.assertTrue(add);
        Assert.assertTrue(remove);
    }

    @Test
    public void removeItemFromCartFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        Assert.assertEquals(0,u.getNumOfItemInCart());
        boolean remove = u.removeItemFromCart(birdToy);
        Assert.assertFalse(remove);
    }

    @Test
    public void changeItemQuantityTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(birdToy, 5);
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,8);
        Assert.assertTrue(change);
        Assert.assertEquals(8,u.getItemQuantity(birdToy));
    }

    @Test
    public void changeItemQuantityTest2(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(birdToy, 5);
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,0);
        Assert.assertTrue(change);
        Assert.assertFalse(u.checkIfCartHas(birdToy));
        Assert.assertEquals(1,u.getNumOfItemInCart());
    }

    @Test
    public void changeItemQuantityFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(birdToy,8);
        Assert.assertFalse(change);
        Assert.assertEquals(0,u.getItemQuantity(birdToy));
    }

    @Test
    public void changeItemQuantityFailTest2(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(catTreat,2);
        boolean change = u.changeQuantityOfItemInCart(catTreat,-1);
        Assert.assertFalse(change);
    }

    @Test
    public void checkOutTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(birdToy, 5);
        u.addItemToCart(catTreat,2);
        Assert.assertEquals(2,u.getNumOfItemInCart());
        Assert.assertEquals(0,u.getNumOfOrderHistory());

        boolean result = u.checkOut();
        Assert.assertEquals(0,u.getNumOfItemInCart());
        Assert.assertEquals(1,u.getNumOfOrderHistory());
        Assert.assertTrue(result);
    }


    @Test
    public void checkOutFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        Assert.assertEquals(0,u.getNumOfItemInCart());
        Assert.assertEquals(0,u.getNumOfOrderHistory());

        boolean result = u.checkOut();
        Assert.assertEquals(0,u.getNumOfOrderHistory());
        Assert.assertFalse(result);
    }

    @Test
    public void getOrderHistoryTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.addItemToCart(birdToy, 5);
        u.checkOut();
        u.addItemToCart(birdToy, 2);
        u.checkOut();
        u.addItemToCart(dogFood, 1);
        u.checkOut();

        Assert.assertEquals(3,u.getOrderHistory().size());
        Assert.assertTrue(u.getOrderHistory().get(0).getShoppingCart().containsKey(birdToy));
        Assert.assertTrue(u.getOrderHistory().get(1).getShoppingCart().containsKey(birdToy));
        Assert.assertTrue(u.getOrderHistory().get(2).getShoppingCart().containsKey(dogFood));
    }

    @Test
    public void createWishListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        Assert.assertEquals(1,u.getWishLists().size());
        Assert.assertEquals("myList",u.findWishList(10).getListName());
    }

    @Test
    public void renameWishListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        u.renameWishList(10,"newName");
        Assert.assertEquals("newName",u.findWishList(10).getListName());
    }

    @Test
    public void removeWishListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        u.removeWishList(10);
        Assert.assertEquals(0,u.getWishLists().size());
    }

    @Test
    public void cannotFindWishListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        Assert.assertNull(u.findWishList(100000));
    }
    @Test
    public void addItemToListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        boolean add1 = u.addItemToWishList(birdToy,10);
        boolean add2 = u.addItemToWishList(catTreat,10);
        Assert.assertTrue(add1);
        Assert.assertTrue(add2);
        Assert.assertEquals(birdToy,u.findWishList(10).getItem("B4"));
        Assert.assertEquals(catTreat,u.findWishList(10).getItem("B5"));
    }
    @Test
    public void addItemToListFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(20, "DogWishList");
        boolean add = u.addItemToWishList(birdToy,909);
        Assert.assertFalse(add);
    }

    @Test
    public void removeItemFromListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        boolean add1 = u.addItemToWishList(birdToy,10);
        boolean add2 = u.addItemToWishList(dogFood,10);
        boolean remove = u.removeItemFromWishList(birdToy,10);
        Assert.assertTrue(add1);
        Assert.assertTrue(add2);
        Assert.assertTrue(remove);
        Assert.assertEquals(dogFood,u.findWishList(10).getItem("B3"));
    }


    @Test
    public void removeItemFromListFailTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "myList");
        boolean add = u.addItemToWishList(birdToy,10);
        boolean remove = u.removeItemFromWishList(catTreat,10);
        Assert.assertTrue(add);
        Assert.assertFalse(remove);
        Assert.assertEquals(birdToy,u.findWishList(10).getItem("B4"));
    }

    @Test
    public void clearItemInAWishListTest(){
        User u = new User("leila12","Leila","Hsiao", "password", "leila@gmail.com");
        u.createWishList(10, "mySupplyList");
        u.addItemToWishList(birdToy,10);
        u.addItemToWishList(dogFood,10);
        u.addItemToWishList(catTreat,10);
        Assert.assertEquals(3, u.findWishList(10).getListSize());
        u.clearItemInList(10);
        Assert.assertEquals(0, u.findWishList(10).getListSize());

    }


}
