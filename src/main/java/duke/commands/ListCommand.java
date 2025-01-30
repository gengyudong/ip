package duke.commands;

import duke.tasks.*;
import duke.ui.*;
public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTaskList());
    }
}
