package duke.commands;

import duke.tasks.*;
import duke.ui.*;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws GengException;
}
