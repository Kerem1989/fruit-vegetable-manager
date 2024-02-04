/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Product> allFruitsandVegetables = new ArrayList<Product>();
    public static ArrayList<String> allUniqueItems = new ArrayList<String>();
    public static ArrayList<String> allUniqueItems2 = new ArrayList<String>();
    public static boolean quitProgram = false;
    static String[] menuOptions = {"\n1. Add a product.",
            "2. Search the catalog.",
            "3. Browse the catalog.",
            "4. Remove a product.",
            "5. Exit the program"};
    static String[] catalogOptions = {"1. Browse the catalog.",
            "2. Search the catalog.",
            "3. Go back to the main menu."};

    public static void main(String[] args) {

        showIntroduction();
        runTheMainMenu();
        printQuitProgramMessage();

    }

    public static void showIntroduction() {
        System.out.println("Welcome to the Fruit and Vegetable program.");
        System.out.print("Please enter any key to continue to the meny.");
        String employeeInput = input.nextLine();
    }

    public static void runTheMainMenu() {

        do {
            for (String listOfMenuOptions : menuOptions) {
                System.out.println(listOfMenuOptions);
            }
            System.out.print("Enter one of the following numbers to continue: ");

            try {

                int employeeInput = input.nextInt();
                input.nextLine();

                switch (employeeInput) {
                    case 1 -> addFruitOrVegetable();
                    case 2 -> searchTheCatalog();
                    case 3 -> browseTheCatalog();
                    case 4 -> removeProduct();
                    case 5 -> quitProgram = true;
                    default -> System.out.println("\nThe input is not valid, please enter a number between 1-6. ");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nThe input is not valid, you cannot enter a letter, please enter a number.");
                input.nextLine();
            }

        } while (!quitProgram);
    }

    public static void printQuitProgramMessage() {
        System.out.println("\nThank you for using the Fruit and Vegetable program.");
        System.out.println("All credit goes to Kerem Incorporated.");
    }

    // This method recieves input from the user and creates objects from the class Product.
    public static void addFruitOrVegetable() {
        double userSetPrice = 0.0;
        String userSetPricePerPcsOrKg = "";
        String userSetFruitOrVegetable = "";
        String userSetProductType = "";

        System.out.print("\nPlease enter the name of the product: ");
        String userSetName = input.nextLine();
        Product createdUserProduct = new Product(userSetName, 0, "", "", "");
        System.out.print("Do you want to add price? Please write yes or any key to continue: ");
        String userChoicePrice = input.nextLine();
        if (userChoicePrice.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter a price: ");
            userSetPrice = input.nextDouble();
            input.nextLine();
            System.out.print("Enter if price is per piece (pcs) or per kilogram (kg). Write pcs or kg: ");
            userSetPricePerPcsOrKg = input.nextLine();
            createdUserProduct = new Product(userSetName, userSetPrice, userSetPricePerPcsOrKg, "", "");
        }
        System.out.print("Do you want add if product is a fruit or vegetable? Please write yes or any key to continue: ");
        String userChoiceFruitOrVegetable = input.nextLine();
        if (userChoiceFruitOrVegetable.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter if product is a fruit or vegetable: ");
            userSetFruitOrVegetable = input.nextLine();
            createdUserProduct = new Product(userSetName, userSetPrice, userSetPricePerPcsOrKg, userSetFruitOrVegetable, "");
        }
        System.out.print("Do you want to add the producttype? Please write yes or any key to continue: ");
        String userChoiceProductType = input.nextLine();
        if (userChoiceProductType.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter the producttype: ");
            userSetProductType = input.nextLine();

        }
        createdUserProduct = new Product(userSetName, userSetPrice, userSetPricePerPcsOrKg, userSetFruitOrVegetable, userSetProductType);
        System.out.print("\n\033[1mYou added: \033[0m");
        System.out.print(createdUserProduct.toString());
        System.out.println("");
        allFruitsandVegetables.add(createdUserProduct);
    }

    /*
    This method recives user input to search the catalog for results,
    displays the result to the user and offers the option to calculate and display price.
     */
    public static void searchTheCatalog() {
        boolean runSearchCatalog = false;

        do {
            System.out.print("\nPlease enter a keyword: ");
            String searchTerm = input.nextLine().trim();

            if (searchTerm.isEmpty()) {
                System.out.println("\nYou cannot leave it blank, please try again.");
                return;
            }

            boolean resultFoundInCatalog = false;

            for (Product catalog : allFruitsandVegetables) {
                if (catalog.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.println("\n\033[1mThe result of your search:\033[0m");
                    System.out.println(catalog.toString());
                    System.out.println("\nDo you want to calculate price for the product?");
                    System.out.print("Please enter yes to accept or any key to decline: ");
                    String userChoiceYesOrNo = input.nextLine();
                    if (userChoiceYesOrNo.equalsIgnoreCase("yes")) {
                        System.out.print("Please enter quantity: ");
                        double userChoiceQuantity = input.nextDouble();
                        input.nextLine();
                        calculatePrice(userChoiceQuantity, catalog.getPrice(), catalog);
                    }
                    resultFoundInCatalog = true;
                }
            }
            if (!resultFoundInCatalog) {
                System.out.println("\nNo search results found.");
            }
            System.out.print("Do you want to exit to the main menu? Write Yes to exit or any key to try again: ");
            String exitSearchCatalog = input.nextLine();
            if (exitSearchCatalog.equalsIgnoreCase("Yes")) {
                runSearchCatalog = true;
            }

        } while (!runSearchCatalog);
    }

    // This method recieves user input to navigate the main menu of the catalog section of this program.
    public static void browseTheCatalog() {
        boolean quitCatalog = false;
        System.out.println("\nWelcome to the product catalog, do you want to:");
        do {
            for (String browsingCatalog : catalogOptions) {
                System.out.println(browsingCatalog);
            }
            System.out.print("Please enter a number: ");
            int userCatalogChoice = input.nextInt();
            input.nextLine();
            if (userCatalogChoice == 1) {
                findUniquePrimaryType();
                System.out.print("Please choose from the options: ");
                findUniqueProductType();
                System.out.print("Please choose from the options: ");
                printFullItemFromInput();
            } else if (userCatalogChoice == 2) {
                searchTheCatalog();
            } else if (userCatalogChoice == 3) {
                quitCatalog = true;
            }
        } while (!quitCatalog);
    }

    // This method receives input from the user to remove objects of the class Product from an arraylist.
    public static void removeProduct() {
        boolean runRemoveProduct = false;

        do {
            boolean doesProductExist = false;
            System.out.print("\nPlease enter the name of the product you want to remove: ");
            String userChoice = input.nextLine();
            for (int i = 0; i < allFruitsandVegetables.size(); i++) {
                if (allFruitsandVegetables.get(i).getName().equalsIgnoreCase(userChoice)) {
                    Product removedProduct = allFruitsandVegetables.remove(i);
                    System.out.println("You removed " + removedProduct.getName() + ".");
                    doesProductExist = true;
                }
            }
            if (!doesProductExist) {
                System.out.println("\nThis product does not exist.");
            }
            System.out.print("Type yes to exit or any key to begin again: ");
            String userChoiceToExit = input.nextLine();
            if (userChoiceToExit.equalsIgnoreCase("Yes")) {
                runRemoveProduct = true;
            }

        } while (!runRemoveProduct);


    }

    // This method receives three parameters and then calculates a price for the user to see.
    public static void calculatePrice(double quantity, double price, Product thisProduct) {
        double sum = quantity * price;
        System.out.println("The total price will be: " + sum + " kr." + " (" + price + " kr/" + thisProduct.getPricePerPcsOrKg() + ")");
    }

    // This method iterates trough arrays using for each loop to find the unique properties to print out for the user to select.
    public static void findUniquePrimaryType() {
        allUniqueItems.clear();
        for (Product uniqueItem : allFruitsandVegetables) {
            boolean doesItemExist = false;
            for (String uniqueProduct : allUniqueItems) {
                if (uniqueProduct.equalsIgnoreCase(uniqueItem.getFruitOrVegetable().toLowerCase())) {
                    doesItemExist = true;
                }
            }
            if (!doesItemExist) {
                allUniqueItems.add(uniqueItem.getFruitOrVegetable().toLowerCase());
            }
            doesItemExist = false;
        }
        System.out.println("\nCategory options: ");
        for (String primaryTypes : allUniqueItems) {
            System.out.println(primaryTypes);
        }
    }

    // This method iterates trough arrays using for each loop to find the unique properties to print out for the user to select.
    public static void findUniqueProductType() {
        String firstUserCatalogChoice = input.nextLine().toLowerCase();
        if (allUniqueItems.contains(firstUserCatalogChoice)) {
            allUniqueItems2.clear();
            for (Product uniqueItem2 : allFruitsandVegetables) {
                if (uniqueItem2.getFruitOrVegetable().equalsIgnoreCase(firstUserCatalogChoice.toLowerCase())) {
                    boolean doesItemExist = false;
                    for (String uniqueItems2 : allUniqueItems2) {
                        if (uniqueItems2.equalsIgnoreCase(uniqueItem2.getProductType().toLowerCase())) {
                            doesItemExist = true;
                        }
                    }
                    if (!doesItemExist) {
                        allUniqueItems2.add(uniqueItem2.getProductType().toLowerCase());

                    }
                    doesItemExist = false;
                }
            }
            System.out.println("\nCategory options: ");
            for (String secondaryTypes : allUniqueItems2) {
                System.out.println(secondaryTypes);
            }
        }
    }

    // This method uses a for-each loop to search for a match based on the user input to print out the full product.
    public static void printFullItemFromInput() {
        String firstUserCatalogChoice = input.nextLine().toLowerCase();
        if (allUniqueItems2.contains(firstUserCatalogChoice.toLowerCase())) {
            System.out.println("\n\033[1mFull products: \033[0m");
            for (Product fullProduct : allFruitsandVegetables) {
                if (fullProduct.getProductType().equalsIgnoreCase(firstUserCatalogChoice.toLowerCase())) {
                    System.out.println(fullProduct.toString());
                    System.out.println("");
                }
            }
        }
    }
}