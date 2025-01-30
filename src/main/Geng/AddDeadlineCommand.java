public class AddDeadlineCommand implements Command {
    private final String description;
    private final String deadline;

    public AddDeadlineCommand(String input) throws GengException {
        try {
            String[] parts = input.substring(9).split(" /by ");
            this.description = parts[0];
            this.deadline = parts[1];
        } catch (Exception e) {
            throw new GengException("Invalid deadline format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new Deadlines(this.description, this.deadline);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
