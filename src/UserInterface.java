import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {

        // Get User's Name -> testing out scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);

        char choice = ' ';
        boolean loadData = true;  //TODO: change this back to run
        while (choice != 'e') {
            System.out.print("Menu: help (h) load data (l) get data (g) exit (e)");
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 'h':
                    System.out.println("help instructions");
                    break;
                case 'l':
                    if (!loadData){
                        System.out.println("call load data function here");
                        loadData = true;
                    } else {
                        System.out.println("data is already loaded");
                    }
                    break;
                case 'e':
                    System.out.println("exiting the program");
                    break;
                case 'g':
                    if (loadData) {
                        System.out.println("What are you looking for? (input 'o' to see options)");
                        String valueSearchingFor = scanner.next();
                        if (valueSearchingFor == "o"){
                            //print all categories
                            for (Category cat : Category.values()) {
                                System.out.println(cat);
                            }
                        }
                        // if value == category
                        for (Category cat : Category.values()) {
                            // check if ==
                        }
                        System.out.println("Which category do you know? (input 'o' to see options)");
                        String categoryKnown = scanner.next();
                        if (categoryKnown == "o"){
                            //print all categories
                            for (Category cat : Category.values()) {
                                System.out.println(cat);
                            }
                        }
                        // if value == category
                        for (Category cat : Category.values()) {
                            // check if ==
                        }
                        System.out.println("What's it's value?");
                        String valueKnown = scanner.next();
                    } else {
                        System.out.println("please load the data");
                    }
                    break;
                default:
                    System.out.println("please pick valid option");
                    break;
            }
        }
    }

    public enum Category {
        SHOW_ID, TITLE, DIRECTOR, DATE_ADDED, RATING, DURATION, DESCRIPTION, CAST, COUNTRY, RELEASE_YEAR
    }
}
