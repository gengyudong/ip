package duke.ui;

import duke.commands.*;
import duke.ui.GengException;

public class Parser {
    public Command parseInput(String input) throws GengException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("list-date")) {
            return new ListDateCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else {
            throw new GengException("My brain is not big enough to understand... Input: todo/deadline/event");
        }
    }
}
