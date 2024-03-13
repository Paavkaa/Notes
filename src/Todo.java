import java.util.Scanner;
import java.util.UUID;

public class Todo {
    Scanner scanner = new Scanner(System.in);

    UUID idTodo;
    private boolean done;
    private String deadline;
    private enum PriorityScale {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    private PriorityScale priority;
    private String description;

    // This is no longer a static method
    public void createTodo() {

        idTodo = UUID.randomUUID();

        // deadline
        System.out.println("Enter the deadline (yyyy-MM-dd HH:mm:ss): ");
        deadline = scanner.nextLine();

        setPriority();

        setDescription(description);

        // setting done value to false
        done = false;
    }

    public void createListItem() {
        idTodo = UUID.randomUUID();

        setDescription(description);

        done = false;
    }

    public void setDescription(String description) {
        System.out.println("Enter the description: ");
        this.description = scanner.nextLine();
    }

    public void setPriority() {
        System.out.println("Enter the priority: ");
        for (int i = 0; i < PriorityScale.values().length; i++) {
            System.out.println((i + 1) + ". " + PriorityScale.values()[i]);
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (choice < 1 || choice > PriorityScale.values().length) {
            System.out.println("Invalid choice. Defaulting to NONE.");
            this.priority = PriorityScale.NONE; // Set instance variable to default
        } else {
            this.priority = PriorityScale.values()[choice - 1]; // Set instance variable based on user choice
        }
    }

    public void viewTodo() {
        System.out.println(
                "Deadline: " + deadline
                + "\nPriority: " + priority
                + "\nDescription: " + description
                + "\nDone: " + done);

        System.out.println("Do you want to mark this todo as done? (y/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            done = true;
        }
    }

    public void viewListItem() {
        System.out.println(
                "Description: " + description
                + "\nDone: " + done);

        System.out.println("Do you want to mark this item as done? (y/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            done = true;
        }
    }
}