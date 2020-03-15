package shop;


import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class ApiConnection {

    public static void main(String[] args) {

    }

    public static String createApiQuery(String search){
        StringBuilder sb = new StringBuilder();
        sb.append("https://amazon-price1.p.rapidapi.com/search?keywords=headphones")
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

}
