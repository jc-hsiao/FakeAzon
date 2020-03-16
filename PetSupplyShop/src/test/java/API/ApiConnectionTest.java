package API;

import org.junit.Test;
import shop.API.ApiConnection;
import static org.junit.Assert.*;

public class ApiConnectionTest {

    @Test
    public void testCreateApiQuery(){
        String search = "headphones";
        String actual =  ApiConnection.createApiQuery(search);
        String expected = "https://amazon-price1.p.rapidapi.com/search?keywords=headphones&marketplace=US";
        assertEquals(expected,actual);
    }

    @Test
    public void testFetchApiQuery(){
        String search = "mouse";
        String resultOfCall = ApiConnection.fetchApiQuery(ApiConnection.createApiQuery(search));
        System.out.println(resultOfCall);
    }

}
