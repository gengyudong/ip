package geng.commands;

import geng.tasks.Task;
import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;


/**
 * Represents a command to delete a task from the task list.
 * This command extracts the task index from user input
 * and removes the specified task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} by parsing the user input to determine the task index.
     *
     * @param input The full user command.
     * @throws GengException If the input format is incorrect or a valid task number is not provided.
     */
    public DeleteCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to delete.");
        }
    }

    /**
     * Executes the command by removing the specified task from the task list,
     * displaying a confirmation message, and saving the updated task list.
     *
     * @param tasks   The task list from which the task is deleted.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If the task number is invalid or there is an error in saving.
     */
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
