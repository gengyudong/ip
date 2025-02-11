package geng.tasks;

/**
 * Represents a To-Do task, which is a type of Task.
 * A To-Do task is characterized by a description and a completion status.
 */
public class ToDos extends Task {

    /**
     * Constructs a To-Do task with the specified description.
     *
     * @param description The description of the To-Do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-Do task.
     * The format is "T | [completion status] [description]".
     *
     * @return A string representation of the To-Do task.
     */
    @Override
    public String toString() {
        return "T |" + super.toString();
    }
}
