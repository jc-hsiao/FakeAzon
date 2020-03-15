package shop;


import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

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





}


//        "ASIN":"B019U00D7K"
//        "title":"COWIN E7 Active Noise Cancelling Headphones Bluetooth Headphones with Microphone Deep Bass Wireless Headphones Over Ear, Comfortable Protein Earpads, 30 Hours Playtime for Travel/Work, Black"
//        "price":"$59.99"
//        "listPrice":"$59.99"
//        "imageUrl":"https://images-na.ssl-images-amazon.com/images/I/41xvqzmxLZL._SL160_.jpg"
//        "detailPageURL":"https://www.amazon.com/Cancelling-Headphones-Bluetooth-Microphone-Comfortable/dp/B019U00D7K"
//        "rating":"4.5"
//        "totalReviews":"18325"
//        "subtitle":"COWIN"
//        "isPrimeEligible":"1"
//        }

