import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Note> notes = new ArrayList<>();
    private final InputChecker inputChecker = new InputChecker();

    public void MainMenu() {
        do {

            //Multi-line text for the menu switch
            System.out.println(
                    """
                    1. Create a new note\s
                    2. Select a note\s
                    3. Exit the program
                    """);

            int choice = inputChecker.getIntegerChoice();

            switch (choice) {
                case 1:
                    addNote(); // Add a new note
                    break;
                case 2:
                    UUID id = selectNote(); // Select a note
                    if (id != null) {
                        NoteMenu(id); // Open the note menu for selected note and it is checked by ID
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

    /**
     * Adds a new note to the collection.
     * This method instantiates a new Note object, prompts the user to create the note's content,
     * and then appends the new note to the 'notes' ArrayList.
     */
    private void addNote() {
        Note note = new Note();
        note.createNote();
        notes.add(note);
    }

    /**
     * Prompts the user to select a note from the list of existing notes.
     * This method displays all notes with their respective titles and indices if the list is not empty.
     * The user is then asked to enter the index of the note they wish to select. If the provided index
     * is valid, the method returns the UUID of the selected note. If there are no notes or the index
     * is invalid, the method informs the user and returns null.
     *
     * @return UUID of the selected note or null if no valid selection is made
     */
    private UUID selectNote() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return null;
        }

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println((i + 1) + ". " + note.getTitle());
        }

        int index;

        try {
            index = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
            return null; // Or ask again using a loop
        } finally {
            scanner.nextLine(); // Consume the newline character whether exception occurred or not
        }


        if (index < 1 || index > notes.size()) {
            System.out.println("Invalid index. Please select a valid note index.");
            return null;
        }

        Note selectedNote = notes.get(index - 1);
        return selectedNote.getId();
    }


    /**
     * Edits the contents of a note identified by its UUID.
     * This method iterates through the list of notes to find the one matching the provided UUID.
     * If a note with the matching UUID is found, its 'editNote' method is called, allowing for the
     * modification of the note's content. If no note with the specified UUID exists, an error message
     * is displayed to the user.
     *
     * @param id UUID of the note to be edited
     */
    private void editNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.editNote();
                return;
            }
        }
        System.out.println("Note with the id " + id + " not found.");
    }

    /**
     * Removes a note from the collection based on its UUID.
     * This method utilizes the 'removeIf' method from the Collection framework to find and remove
     * the note whose UUID matches the given 'id'. If no matching note is found, the collection remains
     * unchanged. There is no return value or confirmation; the note is simply removed if it exists.
     *
     * @param id UUID of the note to be deleted
     */
    private void deleteNote(UUID id) {
        notes.removeIf(note -> note.getId().equals(id));
    }


    /**
     * Displays the content of a specific note identified by its UUID.
     * Iterates through the list of notes to find one with a matching UUID. When found, the note's
     * 'viewNote' method is called, which is responsible for displaying the note's content. If no note
     * with the provided UUID can be located, a message is displayed to inform the user of the
     * unsuccessful search.
     *
     * @param id UUID of the note to be viewed
     */
    private void viewNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.viewNote(id);
                return;
            }
        }
        System.out.println("Note with the id " + id + " not found.");
    }

    /**
     * Displays the note management menu and processes user actions for a specific note.
     * This method presents a menu with options to edit, view, delete a note, or return to the main menu.
     * It reads the user's choice and performs the corresponding action on the note identified by the UUID 'id'.
     * If the user chooses to delete the note or return to the main menu, the method exits after performing
     * the action. If an invalid choice is entered, an error message is displayed, and the menu is presented again.
     *
     * @param id UUID of the note for which the menu actions will be applied
     */
    public void NoteMenu(UUID id) {
        do {

            System.out.println(
                    """
                    1. Edit note\s
                    2. View note\s
                    3. Delete note\s
                    4. Return to main menu
                    """);

                int choice = inputChecker.getIntegerChoice();
// Proceed with the rest of your code using the 'choice' variable

            switch (choice) {
                case 1:
                    editNote(id);
                    break;
                case 2:
                    viewNote(id);
                    break;
                case 3:
                    deleteNote(id);
                    return; // Return to main menu after deletion
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