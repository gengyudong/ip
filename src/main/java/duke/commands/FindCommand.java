package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.GengException;
import duke.ui.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String input) throws GengException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GengException("The keyword of a find command cannot be empty.");
        }
        this.keyword = parts[1].toLowerCase();
    }

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
