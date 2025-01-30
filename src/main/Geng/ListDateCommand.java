public class ListDateCommand implements Command {
    private final String date;

    public ListDateCommand(String date) {
        this.date = date;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        ui.showTaskListByDate(date, tasks.getTaskList());
    }
}
