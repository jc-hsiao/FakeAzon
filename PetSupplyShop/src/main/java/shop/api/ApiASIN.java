package shop.api;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class ApiASIN {

    public static String createApiQueryFromASIN(String id){
        return "https://amazon-price1.p.rapidapi.com/priceReport?asin=" + id + "&marketplace=US";
    }

    public static String fetchApiQueryForASIN(String query) {
        HttpResponse<String> response = Unirest.get(query)
                .header("x-rapidapi-host", "amazon-price1.p.rapidapi.com")
                .header("x-rapidapi-key", "YvO7ZbPbFQmsh0TpNIIxr6DR7pgCp18h5VrjsnqmG1sN2ByovE")
                .asString();

        return response.getBody();
    }

}
