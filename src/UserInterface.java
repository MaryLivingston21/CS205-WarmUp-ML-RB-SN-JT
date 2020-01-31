import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {

        // Get User's Name -> testing out scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);

        char choice = 'h';
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
                    } else {
                        System.out.println("data is already loaded");
                    }
                    break;
                case 'e':
                    System.out.println("exiting program");
                    break;
                case 'g':
                    if (loadData) {
                        System.out.println("get data");
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
}
