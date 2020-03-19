package shop.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiConnection {


    public static String createApiQuery(String search){
        return "https://amazon-price1.p.rapidapi.com/search?keywords=" +
                search +
                "&marketplace=US";
    }

    public static String fetchApiQuery(String query){
        HttpResponse<String> response = Unirest.get(query)
                .header("x-rapidapi-host", "amazon-price1.p.rapidapi.com")
                .header("x-rapidapi-key", "YvO7ZbPbFQmsh0TpNIIxr6DR7pgCp18h5VrjsnqmG1sN2ByovE")
                .asString();

        return response.getBody();
    }


    public static void main(String[] args) throws IOException {
        ArrayList<AmazonItem> a =jsonLoadData("headphones");
        for (AmazonItem item :a) {
            System.out.println(item.getTitle());
            System.out.println(item.getPrice());
        }

    }

    public static ArrayList<AmazonItem> jsonLoadData(String query) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(fetchApiQuery(createApiQuery(query)), new TypeReference<ArrayList<AmazonItem>>() {});
    }



}





