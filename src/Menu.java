import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    int choice;

    NoteArray note = new NoteArray();

    public void displayMenu() {
        do {
            System.out.println(
                    """
                    1. Create a new note\s
                    2. Edit a note\s
                    3. Delete a note\s
                    4. View a note\s
                    5. View all notes\s
                    6. Exit the program
                    """);

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    note.addNote();
                    break;
                case 2:
                    note.editNote();
                    break;
                case 3:
                    note.deleteNote();
                    break;
                case 4:
                    note.viewNote();
                    break;
                case 5:
                    note.viewAllNotes();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            makeSpace();
        } while (true);
    }

    private static void makeSpace() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}
