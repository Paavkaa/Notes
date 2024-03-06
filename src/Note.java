import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
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

        // Select menu for NoteType
        System.out.println("Select the type of the note:");
        int optionNum = 1;
        for (NoteType type : NoteType.values()) {
            System.out.println(optionNum + ". " + type);
            optionNum++;
        }
        int choice = scanner.nextInt();
        if (choice < 1 || choice > NoteType.values().length) {
            System.out.println("Invalid choice. Defaulting to TEXT.");
            this.type = NoteType.TEXT;
        } else {
            this.type = NoteType.values()[choice - 1];
        }

        // date format is "yyyy-MM-dd HH:mm:ss"
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
