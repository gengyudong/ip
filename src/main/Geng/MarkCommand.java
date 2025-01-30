public class MarkCommand implements Command {
    private final int taskIndex;

    public MarkCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GengException("Invalid task number.");
        }
        Task task = tasks.getTask(taskIndex);
        task.markComplete();
        ui.showTaskMarked(task);
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
