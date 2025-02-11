package geng.tasks;

/**
 * Represents a task with a description and completion status.
 * The task can be marked as complete or incomplete.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initialized as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markUncomplete() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     * The completion status is represented as "1" for complete and "0" for incomplete.
     *
     * @return A string representing the task's completion status and description.
     */
    @Override
    public String toString() {
        String completeIcon = (isDone ? " 1 |" : " 0 |");
        return completeIcon + " " + this.description;
    }
}
