package API;

import org.junit.Assert;
import org.junit.Test;
import shop.api.ApiASIN;
import shop.api.ApiConnection;

public class ApiASINTest {

    @Test
    public void createApiQueryFromASINTest() {
        String asin = "B07ZH393HQ";
        String expected = "https://amazon-price1.p.rapidapi.com/priceReport?asin=" + asin + "&marketplace=US";
        String actual = ApiASIN.createApiQueryFromASIN(asin);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fetchApiQueryForASINTest(){
        String search = "B07ZH393HQ";
        String resultOfCall = ApiConnection.fetchApiQuery(ApiConnection.createApiQuery(search));
        System.out.println(resultOfCall);
    }

}

