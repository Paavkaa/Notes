import java.util.ArrayList;
import java.util.Scanner;

public class NoteArray {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Note> notes = new ArrayList<>();

    public void addNote() {
        Note note = new Note();
        note.createNote();
        notes.add(note);
    }

    public void editNote() {
        System.out.println("Enter the title of the note you want to edit: ");
        String title = scanner.nextLine();

        for (Note note : notes) {
            if (note.getTitle().equals(title)) {
                note.editNote();
                return;
            }
        }

        System.out.println("Note with the title " + title + " not found.");
    }

    public void deleteNote() {
        System.out.println("Enter the title of the note you want to delete: ");
        String title = scanner.nextLine();

        for (Note note : notes) {
            if (note.getTitle().equals(title)) {
                notes.remove(note);
                return;
            }
        }

        System.out.println("Note with the title " + title + " not found.");
    }

    public void viewNote() {
        System.out.println("Enter the title of the note you want to view: ");
        String title = scanner.nextLine();

        for (Note note : notes) {
            if (note.getTitle().equals(title)) {
                note.viewNote();
                return;
            }
        }

        System.out.println("Note with the title " + title + " not found.");
    }

    public void viewAllNotes() {
        for (Note note : notes) {
            String title = note.getTitle();
            System.out.println(title);
        }
    }
}
