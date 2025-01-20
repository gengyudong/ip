public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markComplete() {
        this.isDone = true;
    }

    public void markUncomplete() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        String completeIcon = (isDone ? "[X]" : "[ ]");
        return completeIcon + " " + this.description;
    }
}
