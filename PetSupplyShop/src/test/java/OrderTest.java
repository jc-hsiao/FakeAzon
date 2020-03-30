import org.junit.Assert;
import org.junit.Test;
import shop.models.Item;
import shop.models.Order;
import shop.models.User;

import java.util.Date;

public class OrderTest {

    User user = new User("johnDoe","John","Doe", "password", "johnDoe@gmail.com");

    @Test
    public void getUSerTest(){
        Order order = new Order(user);

        User expectedUser =  user;
        User actualUser =  order.getUser();
        Assert.assertEquals(expectedUser , actualUser);
    }
    @Test
    public void getDateTest(){
        Order order = new Order(user);

        Date expectedDate = new Date();
        Date actualDate =  order.getDate();

        Assert.assertTrue(expectedDate.equals(actualDate));
    }
    @Test
    public void getShoppingCartTest(){
        user.addItemToCart(new Item(),2);
        user.addItemToCart(new Item(),1);
        user.addItemToCart(new Item(),5);
        Order order = new Order(user);

        Assert.assertEquals(3 , order.getShoppingCart().size());
    }
}
