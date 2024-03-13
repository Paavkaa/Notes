import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    UUID id;

    /*Types of notes, that can be created*/
    public enum NoteType {
        TEXT, // Simple text note
        LIST, // List of TODOs
        TODO // Note that can be marked as done, has a deadline and priority, can be added description
    }


    /*General values for all note types*/
    private String title;
    private NoteType type;
    private String creationDate;

    // Values for TEXT
    private String text;

    private String[] list;

    Todo todo = new Todo();

    Scanner scanner = new Scanner(System.in);

    /*Constructor for creating a note*/
    public Note(String title, NoteType type, String date) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.type = type;
        this.creationDate = date;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getList() {
        return list;
    }

    public void setList(String[] list) {
        this.list = list;
    }

    public void createNote() {
        id = UUID.randomUUID();
        System.out.println("Title: ");
        this.title = scanner.nextLine();
        this.type = selectType();

        switch (type)
        {
            case TEXT:
                System.out.println("Text: ");
                this.text = scanner.nextLine();
                break;
            case LIST:
                createList();
                break;
            case TODO:
                todo.createTodo();
                break;
        }
        // date format is "yyyy-MM-dd HH:mm:ss"
        this.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void createList() {
        while (true) {
            todo.createListItem();
            System.out.println("Do you want to add another item? (y/N) ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break; // Exit the loop if the user enters 'N'
            }
        }
    }

    public void viewNote(UUID id) {
        System.out.println(
                "Title: " + title
                + "\nType: " + type
                + "\nDate: " + creationDate);

        switch (type)
        {
            case TEXT:
                System.out.println("Text: " + text);
                break;
            case LIST:
                //there will be creating of list note
                System.out.println("Feature is not implemented yet");
                break;
            case TODO:
                todo.viewTodo();
                break;
        }
    }

    public void viewList(UUID id) {
        System.out.println(
                "Title: " + title
                + "\nType: " + type
                + "\nDate: " + creationDate);

        for (String item : list) {
            System.out.println(item);
        }
    }

    public void editNote() {
        System.out.println("What do you want to edit?" +
                "\n1. Title" +
                "\n2. Type" +
                "\n3. Exit" +
                "\nEnter your choice: ");

        int choice = scanner.nextInt();
        // clear the buffer
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new title: ");
                this.title = scanner.nextLine();
                break;

            case 2:
                selectType();
                break;

            case 3:
                System.out.println("Exiting edit mode.");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public NoteType selectType() {
        System.out.println("Select the type of the note:");
        int optionNum = 1;
        for (NoteType type : NoteType.values()) {
            System.out.println(optionNum + ". " + type);
            optionNum++;
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            if (choice < 1 || choice > NoteType.values().length) {
                throw new IllegalArgumentException("Invalid choice."); // Use a more specific exception
            }
            return NoteType.values()[choice - 1];
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Defaulting to TEXT.");
            return NoteType.TEXT;
        }
    }
}