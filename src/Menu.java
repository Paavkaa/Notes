import java.util.Scanner;
import java.util.UUID;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    int choice;

    NoteArray note = new NoteArray();

    public void MainMenu() {
        do {
            System.out.println(
                    """
                    1. Create a new note\s
                    2. Select a note\s
                    3. Exit the program
                    """);

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    note.addNote();
                    break;
                case 2:
                    NoteMenu(note.selectNote());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            makeSpace();
        } while (true);
    }

    public void NoteMenu(UUID id) {
        do {
            if (id == null) {
                System.out.println("No notes found.");
                return;
            }

            System.out.println(
                    """
                    1. Edit note\s
                    2. Delete note\s
                    3. View note\s
                    4. Return to main menu
                    """);

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    note.editNote(id);
                    break;
                case 2:
                    note.deleteNote(id);
                    return;
                case 3:
                    note.viewNote(id);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (true);

    }

    private static void makeSpace() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}
