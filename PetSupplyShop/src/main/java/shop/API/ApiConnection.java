package shop.API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiConnection {



    public static String createApiQuery(String search){
        StringBuilder sb = new StringBuilder();
        sb.append("https://amazon-price1.p.rapidapi.com/search?keywords=")
                .append(search)
                .append("&marketplace=US");

         return sb.toString();
    }

    public static String fetchApiQuery(String query){
        HttpResponse<String> response = Unirest.get(query)
                .header("x-rapidapi-host", "amazon-price1.p.rapidapi.com")
                .header("x-rapidapi-key", "YvO7ZbPbFQmsh0TpNIIxr6DR7pgCp18h5VrjsnqmG1sN2ByovE")
                .asString();

        return response.getBody();
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(jsonLoadData("headphones"));
//    }

    public static String jsonLoadData(String query) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.readValue(fetchApiQuery(createApiQuery(query)), new TypeReference<String>() {});

        return response;
    }

}





