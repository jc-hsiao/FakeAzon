import org.junit.Assert;
import org.junit.Test;

public class ItemListTest {

    @Test
    public void constructorTest(){
        ItemList l = new ItemList(1,"list1");
        Assert.assertEquals(l.getId(),1);
        Assert.assertEquals(l.getName(),"list1");
        Assert.assertEquals(l.getListSize(),0);
    }

    @Test
    public void getItemTest(){
    }

}
