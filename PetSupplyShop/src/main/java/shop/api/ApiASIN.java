package shop.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.IOException;
import java.util.ArrayList;

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

    public static AmazonItem getItemJsonByASIN(String query) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(fetchApiQueryForASIN(createApiQueryFromASIN(query)), new TypeReference<AmazonItem>() {});
    }

    //B00NJ2M33I
    public static void main(String[] args) throws IOException {
        AmazonItem a = getItemJsonByASIN("B00NJ2M33I");
        System.out.println(a.getTitle());
        System.out.println(a.getASIN());
        System.out.println(a.getPrice());
        System.out.println(a.getImageUrl());
        System.out.println(a.getPrice());
        System.out.println(a.getListPrice());
        System.out.println(a.getDetailPageURL());
        System.out.println(a.getRating());
        System.out.println(a.getSubtitle());
        System.out.println(a.getTotalReviews());
    }


}
