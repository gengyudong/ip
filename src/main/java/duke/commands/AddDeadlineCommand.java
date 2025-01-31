package duke.commands;

import duke.tasks.*;
import duke.ui.*;

/**
 * Represents a command to add a deadline task.
 * This command extracts the task description and deadline from user input
 * and adds a new {@link Deadlines} task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final String deadline;

    /**
     * Constructs an {@code AddDeadlineCommand} by parsing user input.
     *
     * @param input The full user command.
     * @throws GengException If the input format is incorrect.
     */
    public AddDeadlineCommand(String input) throws GengException {
        try {
            String[] parts = input.substring(9).split(" /by ");
            this.description = parts[0];
            this.deadline = parts[1];
        } catch (Exception e) {
            throw new GengException("Invalid deadline format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
        }
    }

    /**
     * Executes the command by adding a new deadline task to the task list,
     * displaying a success message to the user, and saving the updated task list.
     *
     * @param tasks   The task list to which the new task is added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If there is an error in saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new Deadlines(this.description, this.deadline);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}
