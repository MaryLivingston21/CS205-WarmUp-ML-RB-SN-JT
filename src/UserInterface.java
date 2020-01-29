import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to our program! \nEnter your name:  ");
        String name = scanner.next();
        System.out.println("Welcome " + name);

    }
}
