package shop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class _MainApp {

    public static void main(String[] args) {
        printWelcome();
        System.out.println(showItems());
        getStringInput("Select a product to add to your cart.");

    }

    public static void printWelcome(){
        System.out.println("" +
                "**************************************************\n" +
                "********           Welcome To AnimalZon   ********\n" +
                "********                Online            ********\n" +
                "********           Pet Supplies Store     ********\n" +
                "**************************************************\n");
    }

    public static String getStringInput(String prompt){
        Scanner string = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = string.nextLine();
        return userInput;
    }

   public static ArrayList<Item> showItems(){
        Brand brand1 = new Brand(1, "Pedigree");
        Brand brand2 = new Brand(2, "Kong");
        Item item1 = new Item(1, "dry dog food", 10.5, "Pet Food", brand1);
        Item item2 = new Item(2, "wet dog food", 3.5, "Pet Food", brand1);
        Item item3 = new Item(3, "dry cat food", 10.5, "Pet Food", brand1);
        Item item4 = new Item(4, "squeaker rattle", 8.59, "Pet Toys", brand2);
        Item item5 = new Item(5, "jumbler ball", 12.99, "Pet Toys", brand2);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);

        return itemList;
    }


}
