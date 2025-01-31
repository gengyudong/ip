package duke.commands;

import duke.tasks.*;
import duke.ui.*;

/**
 * Represents a command to unmark a task, changing its status back to "not completed".
 * This command modifies the status of the specified task to "not complete" and saves the changes.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the given input to specify the task to unmark.
     * The task index is extracted from the input string and adjusted to be zero-based.
     *
     * @param input The user input containing the task number to unmark.
     * @throws GengException If the input is invalid or the task number is not specified correctly.
     */
    public UnmarkCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to unmark.");
        }
    }

    /**
     * Executes the command by unmarking the specified task (marking it as not completed) and saving the changes.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display the task unmarking status.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If the task number is invalid or any other error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GengException("Invalid task number.");
        }
        Task task = tasks.getTask(taskIndex);
        task.markUncomplete();
        ui.showTaskUnmarked(task);
        storage.saveTasksToFile(tasks.getTaskList());
    }
}