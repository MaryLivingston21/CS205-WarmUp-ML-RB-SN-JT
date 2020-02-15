import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
 

        // Get User's Name -> testing out scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);
        OpSqliteDB jdbc = new OpSqliteDB();     // new Object jdbc
        
        char choice = ' ';
        boolean loadData = false;  //TODO: change this back to run eventually
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
                        jdbc.createTables();  // load csv files, import data, and create tables to Film.db file                       
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
                            //TODO:check if ==
                        }
                        System.out.println("Which category do you know? (input 'o' to see options)");
                        String categoryKnown = scanner.next();
                        if (categoryKnown == "o"){
                            System.out.println("SHOW_ID");
                            System.out.println("TITLE");
                            System.out.println("DIRECTOR");
                        }
                        //TODO: ERROR checking
                        scanner.nextLine();
                        System.out.println("What's it's value?");
                        String valueKnown = scanner.nextLine();

                        
                        // make jdbc connection
                        jdbc.connect();
                        
                        // call query function 
                        System.out.println(valueSearchingFor + " " + categoryKnown + " " +valueKnown);
                        jdbc.query(valueSearchingFor,categoryKnown,valueKnown);
                        // testing for table-specifics
                        jdbc.query("rating", "title", "After");
                        jdbc.query("duration", "title", "After");
                        jdbc.query("director", "title", "A Christmas Prince: The Royal Wedding");
                        jdbc.query("release_year", "director", "A. L. Vijay");
                        jdbc.query("description", "director", "Jorge M. Fontana");
                        // queries for same value 
                        jdbc.query("title", "director", "Jorge M. Fontana");
                        
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