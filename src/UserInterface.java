import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);

        char choice = 'h';
        while (choice != 'e') {
            System.out.print("Menu: help (h) load data (l) get data (g) exit (e)");
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 'h':
                    System.out.println("help instructions");
                    break;
                case 'l':
                    System.out.println("call load data function here");
                    break;
                case 'e':
                    System.out.println("exiting program");
                    break;
                case 'g':
                    System.out.println("get data");
                    break;
            }
        }

    }
}
