import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    int choice;
    Note note = new Note();

    public void displayMenu() {
        do {
            System.out.println(
                    """
                    1. Create a new note\s
                    2. Edit a note\s
                    3. Delete a note\s
                    4. View a note\s
                    5. Exit the program
                    """);

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    note.createNote();
                    break;
                case 2:
                    System.out.println("Editing a note");
                    break;
                case 3:
                    System.out.println("Deleting a note");
                    break;
                case 4:
                    System.out.println("Title: " + note.getTitle());
                    System.out.println("Type: " + note.getType());
                    System.out.println("Date: " + note.getDate());
                    break;
                case 5:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            makeSpace();
        } while (choice != 5);
    }

    private static void makeSpace() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}
