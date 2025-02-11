package geng.commands;

import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;


/**
 * Represents a command to list all tasks that match a specific date.
 * This command filters tasks based on the given date and displays them.
 */
public class ListDateCommand implements Command {
    private final String date;

    /**
     * Constructs a ListDateCommand with the given date.
     *
     * @param date The date to filter tasks by.
     */
    public ListDateCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the command by displaying all tasks that match the given date.
     *
     * @param tasks   The task list containing the tasks to filter.
     * @param ui      The user interface to display the filtered task list.
     * @param storage The storage handler (not used in this command).
     * @throws GengException If an error occurs while filtering or displaying the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        ui.showTaskListByDate(date, tasks.getTaskList());
    }
}
