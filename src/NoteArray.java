import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class NoteArray {

    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Note> notes = new ArrayList<>();

    public void addNote() {
        Note note = new Note();
        note.createNote();
        notes.add(note);
    }

    public void editNote(UUID id) {

        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.editNote();
                return;
            }
        }

        System.out.println("Note with the id " + id + " not found.");
    }

    public void deleteNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
                return;
            }
        }

        System.out.println("Note with the id " + id + " not found.");
    }

    public void viewNote(UUID id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                note.viewNote(id);
                return;
            }
        }

        System.out.println("Note with the title " + id + " not found.");
    }

    public UUID selectNote() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return null;
        }

        Note note;
        for (int i = 0; i < notes.size(); i++) {
            note = notes.get(i);
            String title = note.getTitle();
            System.out.println(i + 1 + ". " + title);
        }

        System.out.println("Enter the index of the note you want to select: ");
        int index = scanner.nextInt();
        note = notes.get(index - 1);

        scanner.nextLine();
        return note.getId();
    }
}
