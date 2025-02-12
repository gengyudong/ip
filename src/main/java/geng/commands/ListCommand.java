package geng.commands;

import geng.tasks.TaskList;
import geng.ui.Storage;
import geng.ui.Ui;


/**
 * Represents a command to list all tasks in the task list.
 * This command displays the tasks stored in the list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by displaying all tasks in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display the task list.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getTaskList());
    }
}
