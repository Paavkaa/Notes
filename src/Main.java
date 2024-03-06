public class Main {
    public static void main(String[] args) {
        Note note = new Note();
        note.createNote();

        System.out.println("Title: " + note.getTitle());
        System.out.println("Type: " + note.getType());
        System.out.println("Date: " + note.getDate());
    }
}
