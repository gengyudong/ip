package geng.commands;

import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;

/**
 * Represents a command that can be executed in the application.
 * Each command performs a specific operation on the task list.
 */
public interface Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param ui      The {@code Ui} responsible for user interactions.
     * @param storage The {@code Storage} used for saving and loading tasks.
     * @throws GengException If an error occurs during command execution.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws GengException;
}
