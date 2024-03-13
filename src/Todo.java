import java.util.Scanner;

public class Todo extends Note {

    private Scanner scanner = new Scanner(System.in);
    private boolean done;
    private String deadline;
    private enum PriorityScale {
        LOW,
        MEDIUM,
        HIGH
    }

    private PriorityScale priority;
    private String description;

    // This is no longer a static method
    public void createTodo() {

        // deadline
        System.out.println("Enter the deadline (yyyy-MM-dd HH:mm:ss): ");
        deadline = scanner.nextLine();

        // priority
        System.out.println("Enter the priority: ");
        for (int i = 0; i < PriorityScale.values().length; i++) {
            System.out.println((i + 1) + ". " + PriorityScale.values()[i]);
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (choice < 1 || choice > PriorityScale.values().length) {
            System.out.println("Invalid choice. Defaulting to LOW.");
            priority = PriorityScale.LOW;
        } else {
            priority = PriorityScale.values()[choice - 1];
        }

        // description
        System.out.println("Enter the description: ");
        description = scanner.nextLine();

        // setting done value to false
        done = false;
    }

    public void viewTodo() {
        System.out.println(
                "Deadline: " + deadline
                + "\nPriority: " + priority
                + "\nDescription: " + description
                + "\nDone: " + done);
    }
}