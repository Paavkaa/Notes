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
                    note.selectNote();
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

        switch (choice) {
            case 2:
                note.editNote();
                break;
            case 3:
                note.deleteNote();
                break;
            case 4:
                note.viewNote();
                break;
        }

    }

    private static void makeSpace() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}
