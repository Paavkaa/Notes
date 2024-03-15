import java.util.Scanner;
import java.util.UUID;

public class Todo {
    // Scanner object for reading user input from the console
    Scanner scanner = new Scanner(System.in);

    // InputChecker object for validating user input
    private final InputChecker inputChecker = new InputChecker();

    // Unique identifier for a Todo item
    UUID idTodo;

    // Boolean flag to mark whether the Todo item is completed
    private boolean done;

    // Deadline for the Todo item, expected to be in a recognizable date format
    private String deadline;

    // Enum to represent the priority scale of the Todo item
    private enum PriorityScale {
        NONE,   // No priority assigned
        LOW,    // Low priority
        MEDIUM, // Medium priority
        HIGH    // High priority
    }

    // Priority of the Todo item, using the defined PriorityScale enum
    private PriorityScale priority;

    // Optional description for the Todo item
    private String description;

    /**
     * Interactively creates a Todo item by prompting the user for various details.
     * It assigns a new unique identifier, sets the deadline, priority, and description.
     * The 'done' status is initialized to false indicating the Todo item is not yet completed.
     */
    public void createTodo() {
        idTodo = UUID.randomUUID(); // Generate a unique identifier for the Todo item

        // Prompt the user to enter a deadline for the Todo item
        System.out.println("Enter the deadline (yyyy-MM-dd HH:mm:ss): ");
        deadline = scanner.nextLine(); // Store the deadline

        setPriority(); // Call a method to set the priority of the Todo item

        setDescription(description); // Call a method to set the description of the Todo item

        done = false; // Initialize the done status to false
    }

    /**
     * Creates a new item for a list, assigning it a unique identifier and prompting the user for a description.
     * The completion status of the item is set to false, indicating that it is not yet done.
     */
    public void createListItem() {
        this.idTodo = UUID.randomUUID(); // Generate a unique identifier for the list item

        System.out.println("Enter the description for the list item:");
        String userDescription = scanner.nextLine(); // Prompt the user for the list item description
        setDescription(userDescription); // Set the description for the list item

        done = false; // Initialize the completion status to false
    }

    /**
     * Sets the description for the Todo item. If the provided description is null or empty,
     * prompts the user to enter a description.
     *
     * @param description The initial description to set, or null/empty to prompt user input.
     */
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            System.out.println("Enter the description: ");
            this.description = scanner.nextLine(); // Read the description from user input
        } else {
            this.description = description; // Use the provided description
        }
    }

    /**
     * Prompts the user to select a priority level for the Todo item from the available PriorityScale values.
     * If the user enters an invalid choice, the priority is defaulted to NONE.
     */
    public void setPriority() {
        System.out.println("Enter the priority: ");
        // Display the available priority levels
        for (int i = 0; i < PriorityScale.values().length; i++) {
            System.out.println((i + 1) + ". " + PriorityScale.values()[i]);
        }
        int choice = inputChecker.getIntegerChoice(); // Prompt the user for a choice

        // Validate the user's choice and set the priority or default to NONE
        if (choice < 1 || choice > PriorityScale.values().length) {
            System.out.println("Invalid choice. Defaulting to NONE.");
            this.priority = PriorityScale.NONE; // Set the priority to default (NONE)
        } else {
            this.priority = PriorityScale.values()[choice - 1]; // Set the priority based on user's choice
        }
    }

    /**
     * Displays the details of the Todo item including deadline, priority, description, and completion status.
     * After displaying the details, it prompts the user to optionally mark the Todo item as done.
     */
    public void viewTodo() {
        // Display the Todo item details
        System.out.println(
                "Deadline: " + deadline
                        + "\nPriority: " + priority
                        + "\nDescription: " + description);
        checked(); // Prompt the user to mark the Todo item as done
    }

    /**
     * Displays the description and completion status of a list item.
     * After presenting the details, it prompts the user to mark the item as done if desired.
     */
    public void viewListItem(String item) {
        // Display the list item's description and completion status
        System.out.println("Description: " + description);
        checked(); // Prompt the user to mark the list item as done
    }

    /**
     * Prompts the user to mark a todo item as completed.
     * The method asks the user to confirm if the todo item should be marked as done.
     * If the user inputs 'y' or 'Y', the item's completion status is updated to true.
     */
    public void checked() {
        System.out.println("Do you want to mark this todo as done? (y/N)");
        // Prompt the user to change the completion status to done
        System.out.println("Do you want to mark this item as done? (y/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            done = true; // Update the completion status if the user chooses 'y' or 'Y'
        }
    }
}