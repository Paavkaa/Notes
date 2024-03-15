import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    // Unique identifier for the note
    private UUID id;

    // Scanner for user input
    private final Scanner scanner = new Scanner(System.in);

    // InputChecker object for validating user input
    private final InputChecker inputChecker = new InputChecker();

    // Types of notes that can be created
    public enum NoteType {
        TEXT, // Simple text note
        LIST, // List of items, such as TODOs
        TODO  // Note with a status, deadline, priority, and optional description
    }

    // General properties for all note types
    private String title;       // Title of the note
    private NoteType type;      // Type of the note
    private String creationDate;// Date when the note was created

    // Properties for specific note types
    private String text;       // Content for TEXT note type
    private ArrayList<String> list; // List to store items
    public Todo todo;         // TODO object for TODO note type


    /**
     * Default constructor for creating an instance of a note without initializing fields.
     * Can be used to create a note object and set properties later.
     */
    public Note() {
        // This constructor intentionally left blank.
        // Fields can be set using setter methods or direct access.
    }

    /*Getters and setters for the fields*/
    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    /**
     * Interactively creates a note by prompting the user for input.
     * It assigns a unique identifier, sets the title, note type, and content based on the note type.
     * The creation date is automatically set to the current date and time in the specified format.
     */
    public void createNote() {
        id = UUID.randomUUID(); // Assign a new unique identifier to the note
        System.out.println("Title: ");
        this.title = scanner.nextLine(); // Set the title of the note from user input
        this.type = selectType(); // Set the type of the note based on user selection

        // Set the content of the note depending on its type
        switch (type) {
            case TEXT:
                System.out.println("Text: ");
                this.text = scanner.nextLine(); // Set the text content for TEXT type note
                break;
            case LIST:
                createList(); // Delegate to createList method for LIST type note
                break;
            case TODO:
                todo.createTodo(); // Delegate to Todo object's createTodo method for TODO type note
                break;
        }

        // Set the creation date to the current date and time in the format "yyyy-MM-dd HH:mm:ss"
        this.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Interactively creates a list of items for the note by repeatedly prompting the user.
     * The user is asked after each item if they wish to continue adding more items.
     * The loop terminates when the user indicates they do not want to add another item.
     */
    public void createList() {
        if (this.todo == null) {
            this.todo = new Todo();
        }

        while (true) {
            todo.createListItem(); // Call method to add a new item to the list
            System.out.println("Do you want to add another item? (Y/n) ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break; // Exit the loop if the user enters 'N' or 'n'
            }
        }
    }

    /**
     * Displays the properties and content of the note identified by the given UUID.
     * The method shows the note's title, type, and creation date, followed by the specific content
     * based on the note's type. If a feature for a specific note type is not yet implemented,
     * it informs the user accordingly.
     *
     * @param id UUID of the note to be viewed. Note: the parameter is not currently used in the method.
     */
    public void viewNote(UUID id) {
        // Display the general properties of the note
        System.out.println(
                "Title: " + title
                        + "\nType: " + type
                        + "\nDate: " + creationDate);

        // Display the content based on the note's type
        switch (type) {
            case TEXT:
                System.out.println("Text: " + text); // Show the text content for TEXT type note
                break;
            case LIST:
                viewList(id); // Delegate to viewList method for LIST type note
                break;
            case TODO:
                todo.viewTodo(); // Delegate to Todo object's viewTodo method for TODO type note
                break;
        }
    }

    /**
     * Displays the details and items of a list-type note identified by the given UUID.
     * The method outputs the note's title, type, and creation date, followed by each item in the list.
     * Assumes that the note is of the LIST type and that the 'list' array contains the items to display.
     *
     * @param id UUID of the list-type note to be viewed. Note: the parameter is not currently used in the method.
     */
    public void viewList(UUID id) {

    }

    /**
     * Provides an interactive menu for the user to edit properties of the note.
     * The user can choose to edit the title or the type of the note, or exit the edit mode.
     * Invalid choices prompt the user to try again.
     */
    public void editNote() {
        System.out.println("What do you want to edit?" +
                "\n1. Title" +
                "\n2. Type" +
                "\n3. Exit" +
                "\nEnter your choice: ");

        int choice = inputChecker.getIntegerChoice(); // Get the user's choice as an integer

        switch (choice) {
            case 1:
                System.out.println("Enter new title: ");
                this.title = scanner.nextLine(); // Update the title of the note
                break;

            case 2:
                this.type = selectType(); // Update the type of the note
                break;

            case 3:
                System.out.println("Exiting edit mode."); // Exit the edit menu
                break;

            default:
                System.out.println("Invalid choice. Please try again."); // Prompt for a valid choice
                break;
        }
    }

    /**
     * Presents a menu to the user for selecting the type of the note from the available NoteType enum values.
     * The user inputs a number corresponding to the desired note type. If the choice is invalid,
     * an exception is thrown and caught within the method, and the default note type TEXT is returned.
     *
     * @return The selected NoteType, or TEXT if an invalid choice is made.
     */
    public NoteType selectType() {
        System.out.println("Select the type of the note:");
        int optionNum = 1;
        for (NoteType type : NoteType.values()) {
            System.out.println(optionNum + ". " + type);
            optionNum++;
        }
        int choice = inputChecker.getIntegerChoice(); // Get the user's choice as an integer

        try {
            if (choice < 1 || choice > NoteType.values().length) {
                throw new IllegalArgumentException("Invalid choice."); // Validate user input
            }
            return NoteType.values()[choice - 1]; // Return the selected note type
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Defaulting to TEXT."); // Handle invalid choice
            return NoteType.TEXT; // Return default note type
        }
    }
}