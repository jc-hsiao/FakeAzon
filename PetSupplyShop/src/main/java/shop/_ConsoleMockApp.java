package shop;

import shop.models.Brand;
import shop.models.Item;
import shop.models.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class _ConsoleMockApp {



    User user;
    String name;
    ArrayList<Item> itemOptions;



    // ************************************************************************************* WELCOME MESSAGE ***********

    public void printWelcome(){
        System.out.println("" +
                "**************************************************\n" +
                "********           Welcome To AnimalZon   ********\n" +
                "********                Online            ********\n" +
                "********           Pet Supplies Store     ********\n" +
                "**************************************************\n");
    }
    // ************************************************************************************* USER CREDENTIALS **********

    public void getUserCredentials(){
        System.out.print("Enter your First Name     : ");
        String firstName  = getStringInput();
        name = firstName;

        System.out.print("Enter your Last Name      : ");
        String lastName = getStringInput();

        System.out.print("Enter your user UserName        : ");
        String username = getStringInput();

        user = new User(username , firstName , lastName, "fishsticks", username + "@gmail.com");

        System.out.println("\n" + "Thank You - " + firstName + " " + lastName + "\n");
    }
    // ************************************************************************************* GET LIST OF ITEMS *********

    public static ArrayList<Item> getListOfItems(){
        Brand brand1 = new Brand(1, "Pedigree");
        Brand brand2 = new Brand(2, "Kong");
        Item item1 = new Item("A1", "dry dog food    ", 10.5,  "Pet Food", brand1);
        Item item2 = new Item("A2", "wet dog food    ", 3.5,   "Pet Food", brand1);
        Item item3 = new Item("A3", "dry cat food    ", 10.5,  "Pet Food", brand1);
        Item item4 = new Item("A4", "squeaker rattle ", 8.59,  "Pet Toys", brand2);
        Item item5 = new Item("A5", "jumbler ball    ", 12.99, "Pet Toys", brand2);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);

        return itemList;
    }
    // ****************************************************************************************  DISPLAY ITEMS *********

    public void displayItems(){
        for(Item element : getListOfItems()){
            System.out.println(element.getItemID() + "  " + element.getItemName() + "  " +
                    element.getPrice() + "  [ BRAND ] = " + element.getBrand().getBrandName());
        }
    }
    // *************************************************************************************** GET NUMBER INPUT ********

    public Integer getNumberInput(){

        boolean validInput = false;
        Integer answer = null;

        do{
            try{
                Scanner scanner =  new Scanner(System.in);
                Integer choice = scanner.nextInt();
                answer = choice;
                validInput = true;

            }catch(InputMismatchException e){
                System.out.print("Error : Enter a #    : ");
            }
        }while(!validInput);

        return answer;
    }
    // *************************************************************************************** GET STRING INPUT ********

    public String getStringInput(){
        String answer = "";
        Scanner scanner =  new Scanner(System.in);
        answer = scanner.nextLine();
        return answer;
    }

    // ******************************************************************* COMPARING EACH ITEM ID VS USER INPUT ********

    public boolean validateUserChoice(String choice){

        for(Item element : getListOfItems()){

            if(choice.equals(element.getItemID())){
                return true;
            }

        }
        return false;
    }

    // ***************************************************************************************** GET ITEM BY ID ********

    public Item getItmById(String choice){
        Item item;
        for(Item element : itemOptions){

            if(choice.equals(element.getItemID())){
                return element;
            }
        }
        return null;
    }

    // ************************************************************** CHECK IF USER WOULD LIKE TO KEEP SHOPPING ********
    public boolean validateContinue(String answer){

        String yORn = answer = answer;
        boolean validChoice =  false;
        while (!validChoice){
            if(yORn.equals("y") || yORn.equals("Y")){
                validChoice =true;
                return  true;
            }else if(yORn.equals("n") || yORn.equals("N")){
                validChoice = true;
                return  false;
            }else{
                //                 "Select a product to add to your cart      : "
                System.out.print("Sorry Invalid Input  - Enter Y or N       : ");
                yORn = getStringInput();
            }
        }
        return validChoice;
    }

    // *************************************************************************** Checking vs Items List **************

    public String checkIfValid(String choice){

        String validInput = null;

        boolean validChoice = false;
        while (validChoice == false){
            if (validateUserChoice(choice)){
                validInput = choice;
                validChoice = true;
            }else{

                System.out.print("Sorry : Invalid input - Try again         : ");
                choice = getStringInput();
            }

        }
        return validInput;
    }


    // ****************************************************************************** PRINTING USERS CART **************
    public void  printUsersCart(){

        System.out.println("\n" + "----------------------------------------------" + "\n" +
                                  ":                ITEMS IN CART               :" + "\n" +
                                  "----------------------------------------------" + "\n");

        for(Item element : this.user.getShoppingCart().getItems().keySet()){
            String name  = element.getItemName();
            Integer quantity = user.getShoppingCart().getItems().get(element);
            Double priceTotal = element.getPrice() * quantity;
            System.out.println(name + " [ Quantity ] = [ " + quantity + " ] Total = [ " + priceTotal + " ]");
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Grand Total                                  =  $" +user.getShoppingCart().getGrandTotal());
        System.out.print("--------------------------------------------------------" + "\n");
    }

    // *************************************************************************** HANDLE REMOVING ITEMSE **************

    public void handleRemovingItem(){

        printUsersCart();
        System.out.print("Select item to remove from your cart      : ");

        String removeChoice = getStringInput();
        removeChoice = checkIfValid(removeChoice);

        if(user.checkIfCartHas(getItmById(removeChoice))){
            System.out.print("Enter amount to remove                    : ");
            Integer amountToRemove = getNumberInput();
            boolean removed = user.changeQuantityOfItemInCart(getItmById(removeChoice) , user.getShoppingCart().getItemAmount(getItmById(removeChoice)) - amountToRemove);
            if(removed){

                System.out.println("\n" + "Item has been removed successfully        : ");
                printUsersCart();
            }else{
                //                 "Would you like to keep shopping -  Y or N : "
                System.out.println("Sorry invalid amount to remove            : ");
            }

        }else{
            System.out.println("ERROR :            Item not found in cart : ");
        }
    }


    // ***********************************************************************************************   MAIN   ********

    public static void main(String[] args) {
        _ConsoleMockApp mainApp = new _ConsoleMockApp();

        mainApp.itemOptions = getListOfItems();

        mainApp.printWelcome();
        mainApp.getUserCredentials();


        boolean stayInStore = true;

        while(stayInStore){

            System.out.println("\n");
            mainApp.displayItems();

            System.out.print("\n" + "Select a product to add to your cart      : ");
            String choice = mainApp.getStringInput();
            choice = mainApp.checkIfValid(choice);

            System.out.print("Enter amount to add                       : ");
            Integer amount = mainApp.getNumberInput();

            mainApp.user.addItemToCart(mainApp.getItmById(choice) , amount);
            mainApp.printUsersCart();


            System.out.print("Would you like to keep shopping -  Y or N : ");
            String yesOrNo = mainApp.getStringInput();

            stayInStore = mainApp.validateContinue(yesOrNo);


        }

        System.out.println("\n" + "            BEFORE CHECKING OUT             ");
        System.out.print("Would you like to remove Items  -  Y or N : ");
        String removeYorN = mainApp.getStringInput();

        boolean readyToRemoveItems = true;

        while (readyToRemoveItems){

            boolean removeItems = mainApp.validateContinue(removeYorN);

            if(removeItems){
                mainApp.handleRemovingItem();
                //                 "Would you like to remove Items  -  Y or N : "
                System.out.print("Remove Additional Items ?       -  Y or N : ");
                removeYorN = mainApp.getStringInput();
                boolean removeAgain = mainApp.validateContinue(removeYorN);
                if(!removeAgain){
                    readyToRemoveItems = false;
                    System.out.println("\n" + "   Thank You For Shopping At AnimalZon   " + "\n");
                }
            }else{
                System.out.println("\n" + "   Thank You For Shopping At AnimalZon   " + "\n");
                readyToRemoveItems = false;

            }

        }

        System.out.println("\n" + "Thank you");
    }
}
