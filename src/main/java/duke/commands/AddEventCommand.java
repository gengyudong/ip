package duke.commands;

import duke.tasks.*;
import duke.ui.*;

/**
 * Represents a command to add an event task.
 * This command extracts the task description, start time, and end time from user input
 * and adds a new {@link Events} task to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final String start;
    private final String end;

    /**
     * Constructs an {@code AddEventCommand} by parsing user input.
     *
     * @param input The full user command.
     * @throws GengException If the input format is incorrect.
     */
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

    /**
     * Executes the command by adding a new event task to the task list,
     * displaying a success message to the user, and saving the updated task list.
     *
     * @param tasks   The task list to which the new task is added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If there is an error in saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new Events(this.description, this.start, this.end);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasksToFile(tasks.getTaskList());
    }
}