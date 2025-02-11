package geng.commands;

import geng.tasks.Task;
import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;


/**
 * Represents a command to mark a task as completed.
 * This command modifies the status of the specified task to "complete" and saves the changes.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the given input to specify the task to mark.
     * The task index is extracted from the input string and adjusted to be zero-based.
     *
     * @param input The user input containing the task number to mark as complete.
     * @throws GengException If the input is invalid or the task number is not specified correctly.
     */
    public MarkCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to mark.");
        }
    }

    /**
     * Executes the command by marking the specified task as complete and saving the changes.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display the task completion status.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If the task number is invalid or any other error occurs.
     */
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
