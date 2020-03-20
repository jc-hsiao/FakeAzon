package API;

import org.junit.Test;
import shop.api.ApiConnection;
import static org.junit.Assert.*;

public class ApiConnectionTest {

    @Test
    public void createApiQueryTest(){
        String search = "headphones";
        String expected = "https://amazon-price1.p.rapidapi.com/search?keywords=headphones&marketplace=US";
        String actual =  ApiConnection.createApiQuery(search);
        assertEquals(expected,actual);
    }

    @Test
    public void fetchApiQueryTest(){
        String search = "mouse";
        String resultOfCall = ApiConnection.fetchApiQuery(ApiConnection.createApiQuery(search));
        System.out.println(resultOfCall);
    }

}
