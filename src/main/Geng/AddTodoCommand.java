public class AddTodoCommand implements Command {
    private final String description;

    public AddTodoCommand(String input) throws GengException {
        if (input.length() <= 5) {
            throw new GengException("The description of a todo cannot be empty.");
        }
        this.description = input.substring(5);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new ToDos(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
