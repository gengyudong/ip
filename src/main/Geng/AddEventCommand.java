public class AddEventCommand implements Command {
    private final String description;
    private final String start;
    private final String end;

    public AddEventCommand(String input) throws GengException {
        try {
            String[] parts = input.substring(6).split(" /from ");
            this.description = parts[0];
            String[] duration = parts[1].split(" /to ");
            this.start = duration[0];
            this.end = duration[1];
        } catch (Exception e) {
            throw new GengException("Invalid event format. Use: event [description] /from [time] /to [time]");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new Events(this.description, this.start, this.end);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
