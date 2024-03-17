import java.util.InputMismatchException;
import java.util.Scanner;

public class InputChecker {

    // Scanner object for reading user input from the console
    Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for an integer choice and handles invalid input.
     *
     * @return The integer choice entered by the user.
     */
    public int getIntegerChoice() {
        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break; // Exit the loop if a valid integer is entered
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the entire line of invalid input
                // Loop will continue to prompt the user for a valid integer
            }
        }
        return choice;
    }
}
