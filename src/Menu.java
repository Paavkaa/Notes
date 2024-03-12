import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Note> notes = new ArrayList<>();

    public void MainMenu() {
        do {
            System.out.println(
                    """
                    1. Create a new note\s
                    2. Select a note\s
                    3. Exit the program
                    """);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    UUID id = selectNote();
                    if (id != null) {
                        NoteMenu(id);
                    }
                    break;
                case 3:
                    scanner.close(); // Close the scanner before exiting
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            makeSpace();
        } while (true);
    }

    private void addNote() {
        Note note = new Note();
        note.createNote();
        notes.add(note);
    }

    private UUID selectNote() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return null;
        }

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println((i + 1) + ". " + note.getTitle());
        }

        System.out.print("Enter the index of the note you want to select: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index < 1 || index > notes.size()) {
            System.out.println("Invalid index. Please select a valid note index.");
            return null;
        }

        Note selectedNote = notes.get(index - 1);
        return selectedNote.getId();
    }

    private void editNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.editNote();
                return;
            }
        }
        System.out.println("Note with the id " + id + " not found.");
    }

    private void deleteNote(UUID id) {
        notes.removeIf(note -> note.getId().equals(id));
    }

    private void viewNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.viewNote(id);
                return;
            }
        }
        System.out.println("Note with the id " + id + " not found.");
    }

    public void NoteMenu(UUID id) {
        do {
            System.out.println(
                    """
                    1. Edit note\s
                    2. Delete note\s
                    3. View note\s
                    4. Return to main menu
                    """);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    editNote(id);
                    break;
                case 2:
                    deleteNote(id);
                    return; // Return to main menu after deletion
                case 3:
                    viewNote(id);
                    break;
                case 4:
                    return; // Return to main menu
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