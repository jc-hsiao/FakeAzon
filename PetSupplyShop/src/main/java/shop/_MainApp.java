package shop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class _MainApp {

    User user;
    String name;

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

        System.out.print("Enter your user ID        : ");
        Integer ID = getNumberInput();

        user = new User(ID , firstName , lastName);

        System.out.println("\n" + "Thank You - " + firstName + " " + lastName + "\n");
    }
    // ************************************************************************************* GET LIST OF ITEMS *********

    public static ArrayList<Item> getListOfItems(){
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

    public boolean validateUserChoice(Integer choice){

        for(Item element : getListOfItems()){

            if(choice == element.getItemID()){
                return true;
            }

        }
        return false;
    }

    // ***************************************************************************************** GET ITEM BY ID ********

    public Item getItmById(Integer choice){
        Item item = null;
        for(Item element : getListOfItems()){

            if(choice == element.getItemID()){
                return element;
            }
        }
        return item;
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
    public Integer checkIfValid(Integer choice){

        Integer validInput = null;

        boolean validChoice = false;
        while (validChoice == false){
            if (validateUserChoice(choice)){
                validInput = choice;
                validChoice = true;
            }else{

                System.out.print("Sorry : Invalid input - Try again         : ");
                choice = getNumberInput();
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
        System.out.println("----------------------------------------------");
        System.out.println("Grand Total =              : " + user.getShoppingCart().getGrandTotal());
        System.out.print("----------------------------------------------" + "\n");
    }
    // ***********************************************************************************************   MAIN   ********

    public static void main(String[] args) {

        _MainApp mainApp = new _MainApp();
        mainApp.printWelcome();
        mainApp.getUserCredentials();


        boolean stayInStore = true;

        while(stayInStore){

            System.out.println("\n");
            mainApp.displayItems();

            System.out.print("\n" + "Select a product to add to your cart      : ");
            Integer choice = mainApp.getNumberInput();
            choice = mainApp.checkIfValid(choice);

            System.out.print("Enter amount to add                       : ");
            Integer amount = mainApp.getNumberInput();

            mainApp.user.addItemToCart(mainApp.getItmById(choice) , amount);
            mainApp.printUsersCart();


            System.out.print("Would you like to keep shopping -  Y or N : ");
            String yesOrNo = mainApp.getStringInput();

            boolean keepShopping = mainApp.validateContinue(yesOrNo);
            if(keepShopping){
                stayInStore = true;
            }else{
                stayInStore = false;


                System.out.println("\n" + "            BEFORE CHECKING OUT             ");

                System.out.println("Would you like to remove Items  -  Y or N : ");
                String removeYorN = mainApp.getStringInput();
                boolean removeItems = mainApp.validateContinue(removeYorN);

                if(removeItems){
                    //               "Would you like to keep shopping -  Y or N : "
                    System.out.print("Select item to remove from your cart      : ");
                    Integer removeChoice = mainApp.getNumberInput();
                    removeChoice = mainApp.checkIfValid(removeChoice);

                    System.out.print("Enter amount to remove                    : ");
                    Integer amountToRemove = mainApp.getNumberInput();

                    if(mainApp.user.checkIfCartHas(mainApp.getItmById(removeChoice))){
                        mainApp.user.changeQuantityOfItemInCart(mainApp.getItmById(removeChoice) , amountToRemove);
                        mainApp.printUsersCart();
                    }

                }
            }


        }

        System.out.println("\n" + "Thank you");

    }



}
