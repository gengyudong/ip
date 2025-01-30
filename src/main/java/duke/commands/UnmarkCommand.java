package duke.commands;

import duke.tasks.*;
import duke.ui.*;
public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (Exception e) {
            throw new GengException("Please specify a valid task number to unmark.");
        }
    }

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
