package geng.commands;


import geng.tasks.Task;
import geng.tasks.TaskList;
import geng.tasks.ToDos;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;

/**
 * Represents a command to add a ToDo task.
 * This command extracts the task description from user input
 * and adds a new {@link ToDos} task to the task list.
 */
public class AddTodoCommand implements Command {
    private final String description;

    /**
     * Constructs an {@code AddTodoCommand} by parsing user input.
     *
     * @param input The full user command.
     * @throws GengException If the input format is incorrect or the description is empty.
     */
    public AddTodoCommand(String input) throws GengException {
        if (input.length() <= 5) {
            throw new GengException("The description of a todo cannot be empty.");
        }
        this.description = input.substring(5);
    }

    /**
     * Executes the command by adding a new ToDo task to the task list,
     * displaying a success message to the user, and saving the updated task list.
     *
     * @param tasks   The task list to which the new task is added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If there is an error in saving the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        Task task = new ToDos(description);
        tasks.addTask(task);
        storage.saveTasksToFile(tasks.getTaskList());
        return ui.showTaskAdded(task, tasks.size());
    }
}
