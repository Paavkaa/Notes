import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Note note = new Note();

        while (true) {
            System.out.println("Welcome to the Notes App!");
            System.out.println(
                    "1. Create a new note " +
                            "\n2. Edit a note " +
                            "\n3. Delete a note " +
                            "\n4. View a note " +
                            "\n5. Exit the program");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    note.createNote();
                    break;
                case 2:
                    System.out.println("Editing a note");
                    break;
                case 3:
                    System.out.println("Deleting a note");
                    break;
                case 4:
                    System.out.println("Title: " + note.getTitle());
                    System.out.println("Type: " + note.getType());
                    System.out.println("Date: " + note.getDate());
                    break;
                case 5:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (choice == 5) {
                break;
            }

        }
    }

}

