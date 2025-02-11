package geng.commands;

import java.util.ArrayList;

import geng.tasks.Task;
import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given input.
     * Extracts and stores the keyword to be searched.
     *
     * @param input The full user input containing the find command and keyword.
     * @throws GengException If no keyword is provided.
     */
    public FindCommand(String input) throws GengException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GengException("The keyword of a find command cannot be empty.");
        }
        this.keyword = parts[1].toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     * Displays matching tasks or an error message if no tasks match.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param ui      The {@code Ui} responsible for user interactions.
     * @param storage The {@code Storage} used for saving and loading tasks (not used in this command).
     * @throws GengException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        ArrayList<Task> matchedTaskList = new ArrayList<>();

        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().toLowerCase().contains(this.keyword)) {
                matchedTaskList.add(task);
            }
        }

        if (matchedTaskList.isEmpty()) {
            ui.showErrorMessage("There is no task that matches the keyword: " + this.keyword);
        } else {
            ui.showTaskList(matchedTaskList);
        }
    }
}
