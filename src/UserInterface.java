import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
 

        // Get User's Name -> testing out scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);

        char choice = ' ';
        boolean loadData = false;
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
                        OpSqliteDB jdbc = new OpSqliteDB();
                        jdbc.createTable();       
                    } else {
                        System.out.println("data is already loaded");
                    }
                    break;
                case 'e':
                    System.out.println("exiting the program");
                    break;
                case 'g':
                    if (loadData) {
                        System.out.println("What are you looking for?");
                        System.out.println("Show_id, Title, Director, Date_Added, Rating, Duration, Description");
                        System.out.println("Cast, Country, Release_year");
                        String valueSearchingFor = scanner.next();
                        System.out.println("Which category do you know?");
                        String categoryKnown = scanner.next();
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
