import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
//show_id	title	director	date_added	rating	duration	description
//netflix_titles	title	director	cast	country	release_year	rating	duration
private static final String[] CATEGORIES = {"show_id", "title", "director", "date_added", "rating", "duration",
                                        "description", "netflix_titles", "cast", "country", "release_year", "duration"};
    public static void main(String[] args) {
        OpSqliteDB jdbc = new OpSqliteDB();     // new Object jdbc

        System.out.println("Welcome to our Movie program!");
        System.out.println("Enter \"help\" for HELP, \"load data\" to load the data and \"exit\" to Leave");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")){
            //GET INPUT
            input = scanner.nextLine();
            String[] spited = input.trim().split("\\s+");
            //HELP
            if(spited[0].trim().equals("help")){
                System.out.println("Enter what you want to find, and what you know already and its name");
                System.out.println("Example: director title \"A Christmas Prince: The Royal Wedding\"");
                System.out.println("Output : John Schultz");

            }
            //LOAD DATA
            else if (spited[0].trim().equals("load")) {
                System.out.println("Loading data");
            }
            //EXIT
            else if (spited[0].trim().equals("exit")) {
                System.out.println("Good Bye");
            }
            //Trying to Query
            else {
                //INCORRECT INPUT LENGTH
                if (spited.length < 3) {
                    System.out.println("not enough arguments entered, give us some more info");
                } else if (spited.length > 3) {
                    System.out.println("to many arguments entered try wrapping your last info in \"movie name\"");
                }
                //CORRECT INPUT LENGTH
                else {
                    //CORRECT INPUT CATEGORIES
                    if (StringInArray(spited[0], CATEGORIES) && StringInArray(spited[1], CATEGORIES)) {
                        String find = spited[0];
                        String from = spited[1];
                        String id = StripQuotes(spited[2]);
                        System.out.println("Making a query for " + find + " from table " + from + " using ID: " + id);
                        // make jdbc connection
                        jdbc.connect();

                        // call query function
                        jdbc.query(find, from, id);
                    } else {
                        //INCORRECT INPUT CATEGORIES
                        if (StringInArray(spited[0], CATEGORIES)) {
                            System.out.println("Sorry we don't know \"" + spited[0] + "\". Try another field. EX: title");
                        } else {
                            System.out.println("Sorry we don't know \"" + spited[1] + "\". Try another field. EX: title");
                        }
                    }
                }
            }

        }

    }

    public static boolean StringInArray(String target, String[] list){
        for (String str : list){
            if(str.equals(target)){
                return true;
            }
        }
        return false;
    }
    public static String StripQuotes(String originalStr){
        if (originalStr.startsWith("\"")) {
            originalStr = originalStr.substring(1);
        }
        if (originalStr.endsWith("\"")) {
            originalStr = originalStr.substring(0, originalStr.length() - 1);
        }
        return  originalStr;
    }

}
