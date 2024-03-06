import java.util.Scanner;
import java.time.LocalDateTime;

public class Note {

    /*Types of notes, that can be created*/
    public enum NoteType {
        TEXT, // Simple text note
        LIST, // List of items that can be checked and unchecked
        TODO, // Note that can be marked as done, has a deadline and priority, can be added description
    }


    /*General values for all note types*/
    private String title;
    private NoteType type;
    private String date;

    Scanner scanner = new Scanner(System.in);

    /*Constructor for creating a note*/
    public Note(String title, NoteType type, String date) {
        this.title = title;
        this.type = type;
        this.date = date;
    }

    public Note() {
    }
    /*Getters and setters for the fields*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void createNote() {
        System.out.println("Title: ");
        this.title = scanner.nextLine();

        System.out.println("Enter the type of the note: \n");
        for (NoteType type : NoteType.values()) {
            System.out.println(type);
        }
        this.type = NoteType.valueOf(scanner.nextLine().toUpperCase());

        this.date = LocalDateTime.now().toString();
    }
}
