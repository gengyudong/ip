public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to delete.");
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GengException("Invalid task number.");
        }
        Task removedTask = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        ui.showTaskDeleted(removedTask, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
