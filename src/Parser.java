import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    private static final String[] CATEGORIES = {"show_id", "title", "director", "date_added", "rating", "duration",
            "description", "netflix_titles", "cast", "country", "release_year", "duration"};
    public static void main(String[] args) {
        //Data Base
        OpSqliteDB jdbc = new OpSqliteDB();     // new Object jdbc
        boolean loadData = false;
        //Parsing
        System.out.println("Welcome to our Movie program!");
        System.out.println("Enter \"help\" for HELP, \"load data\" to load the data and \"exit\" to Leave");
        System.out.println("Enter what you want to find and what you know and its name");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")){
            //GET INPUT
            input = scanner.nextLine();
            String[] spited = input.trim().split("\\s+");

            //FIX multiple world titles
            if(spited.length > 3){
                String searchHeader = "";
                for(int i=2; i<spited.length; i++){
                    searchHeader += spited[i] + " ";
                }
                spited = new String[]{spited[0],spited[1],searchHeader.trim()};
            }

            //HELP
            if(spited[0].trim().equals("help")){
                System.out.println("Enter \"help\" for HELP, \"load data\" to load the data and \"exit\" to Leave");
                System.out.println("Enter what you want to find, and what you know already and its name");
                System.out.println("Example: director title \"A Christmas Prince: The Royal Wedding\"");
                System.out.println("Output : John Schultz");

            }
            //LOAD DATA
            else if (spited[0].trim().equals("load")) {
                if(loadData){
                    System.out.println("data already loaded");
                }else{
                    System.out.println("Loading data");
                    jdbc.createTables();
                    loadData = true;
                    System.out.println("data Loaded");
                }
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
                } else if(loadData){
                    System.out.println("No data loaded yet. You must enter \"load data\" before searching.");
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
        originalStr.trim();
        if (originalStr.startsWith("\"")) {
            originalStr = originalStr.substring(1);
        }
        if (originalStr.endsWith("\"")) {
            originalStr = originalStr.substring(0, originalStr.length() - 1);
        }
        return  originalStr;
    }

}
