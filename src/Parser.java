import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {

    //Array of all categories in the dataset
    private static final String[] CATEGORIES = {"show_id", "title", "director", "date_added", "rating", "duration",
            "description", "netflix_titles", "actor", "country", "release_year", "duration"};

    public static void main(String[] args) {

        //Create Data Base - new Object jdbc
        OpSqliteDB jdbc = new OpSqliteDB();
        boolean loadData = false;

        //Parsing
        System.out.println("Welcome to our Movie program!");
        System.out.println("Enter \"help\" for help, else enter first query.");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Input Handling
        while (!input.equals("exit")){

            //GET INPUT
            input = scanner.nextLine();
            String[] splited = input.trim().split("\\s+");

            //if multiple word titles, concatenate 
            if(splited.length > 3){
                splited = concatenateString(splited);
            }

            //HELP TODO: double check
            if(splited[0].trim().equals("help")){
                System.out.println("Enter \"help\" for help, \"load data\" to load the data, and \"exit\" to leave the program.");
                System.out.println("Enter what you want to find, the category you know, and its value");
                System.out.println("Example: director title \"A Christmas Prince: The Royal Wedding\"");
                System.out.println("Output : John Schultz");

            }
            //LOAD DATA
            else if (splited[0].trim().equals("load")) {
                if(loadData){
                    System.out.println("The data has already been loaded");
                }else{
                    System.out.println("Loading data...");
                    loadData = true;
                    jdbc.createTables();
                    //TODO: error checking if data doesnt load
                    System.out.println("The data is now loaded.");
                }
            }

            //if input == exit
            else if (splited[0].trim().equals("exit")) {
                System.out.println("Program powering down...");
            }
            //query the input
            else {
                // IF INCORRECT INPUT LENGTH
                if (splited.length < 3 || splited.length == 4) {
                    System.out.println("Not enough arguments entered, please try again with more info");
                }
                else if (splited.length > 5) {
                    System.out.println("Too many arguments entered, try wrapping your last info in \"movie name\"");
                }
                else if(!loadData){
                    System.out.println("No data loaded yet. You must enter \"load data\" before searching.");
                }
                //CORRECT INPUT LENGTH
                else {
                    //CORRECT INPUT CATEGORIES
                    // With 3 args
                    if (splited.length == 3) {
                        if (StringInArray(splited[0], CATEGORIES) && StringInArray(splited[1], CATEGORIES)) {
                            String find = splited[0];
                            String from = splited[1];
                            String id = StripQuotes(splited[2]);
                            System.out.println("Making a query for " + find + " from table " + from + " using ID: " + id);

                            // make jdbc connection
                            jdbc.connect();

                            // call query function
                            jdbc.query(find, from, id);

                        } else {
                            //INCORRECT INPUT CATEGORIES
                            if (StringInArray(splited[0], CATEGORIES)) {
                                System.out.println("Sorry we don't know \"" + splited[0] + "\". Try another field. EX: title");
                            } else {
                                System.out.println("Sorry we don't know \"" + splited[1] + "\". Try another field. EX: title");
                            }
                        }
                    }
                    // With 5 args
                    else {
                        if (StringInArray(splited[0], CATEGORIES) && StringInArray(splited[1], CATEGORIES) && StringInArray(splited[3], CATEGORIES)) {
                            String find = splited[0];
                            String from1 = splited[1];
                            String id1 = StripQuotes(splited[2]);
                            String from2 = splited[3];
                            String id2 = StripQuotes(splited[4]);

                            System.out.println("Making a query for " + find + " from col " + from1 + " using ID: " + id1 +
                                    " and from col " + from2 + " using ID: " + id2 );

                            // System.out.println(find + from1 + id1 + from2 + id2);  //testing

                            // make jdbc connection
                            jdbc.connect();

                            // call 5 arg query function
                            // rating country India director A. L. Vijay   //test case
                            // jdbc.query("rating","country","India","director","A. L. Vijay"); // test query
                            jdbc.query(find, from1, id1, from2, id2);

                        } else {
                            //INCORRECT INPUT CATEGORIES
                            if (StringInArray(splited[0], CATEGORIES)) {
                                System.out.println("Sorry we don't know \"" + splited[0] + "\". Try another field. EX: title");
                            } else if (StringInArray(splited[1], CATEGORIES) || StringInArray(splited[3], CATEGORIES)){
                                System.out.println("Sorry we don't know either \"" + splited[1] + "or \"" + splited[3] + "\". Try another field. EX: title");
                            }
                        }
                    }
                    //
                }
            }
        }
    }

    // takes the input (array[string]) and the target value(string)
    // if target value is in array, return true, else false
    public static boolean StringInArray(String target, String[] list){
        for (String str : list){
            if(str.equals(target)){
                return true;
            }
        }
        return false;
    }

    // strips quotes from the beginning and end of strings
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

    // the instance of the data is stored in an array of strings
    // if a value has multiple words, this method adjusts the array so that all words from the value are in the same index
    public static String[] concatenateString(String[] splited){
        if(splited.length > 3) {
            String searchHeader = "";
            String third = "";
            String forth = "";
            String fifth = "";
            Boolean rest = false; 
            for (int i = 2; i < splited.length; i++) {
                if (rest)
                    fifth += splited[i] + " ";
                if (StringInArray(splited[i], CATEGORIES)) {
                    third = searchHeader;
                    forth = splited[i];
                    rest = true;
                }
                searchHeader += splited[i] + " ";
                
            }
            if (fifth != "")
                splited = new String[]{splited[0], splited[1], third.trim(), forth, fifth.trim()};
            else
                splited = new String[]{splited[0], splited[1], searchHeader.trim()};
            return splited;
        }else {
            return splited;
        }
    }

}

