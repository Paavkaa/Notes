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

    /*Constructor for creating a note*/
    public Note(String title, NoteType type, String date) {
        this.title = title;
        this.type = type;
        this.date = date;
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
}
