package geng.ui;

import geng.commands.AddDeadlineCommand;
import geng.commands.AddEventCommand;
import geng.commands.AddTodoCommand;
import geng.commands.Command;
import geng.commands.DeleteCommand;
import geng.commands.FindCommand;
import geng.commands.ListCommand;
import geng.commands.ListDateCommand;
import geng.commands.MarkCommand;
import geng.commands.ShowScheduleCommand;
import geng.commands.UnmarkCommand;
import javafx.application.Platform;

/**
 * The Parser class is responsible for parsing user input commands
 * and returning the corresponding Command object. It interprets
 * the input, creates the appropriate Command, and throws a
 * {@link GengException} if the input is invalid.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     * This method determines the type of command (e.g., "list", "todo", "deadline", etc.)
     * based on the input string and creates the appropriate Command.
     *
     * @param input The user input as a string.
     * @return The corresponding Command object.
     * @throws GengException If the input is invalid or does not match any known command.
     */
    public Command parseInput(String input) throws GengException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("list-date")) {
            return new ListDateCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("show")) {
            return new ShowScheduleCommand(input);
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
        } else if (input.equals("bye")) {
            Platform.exit();
        } else {
            throw new GengException("My brain is not big enough to understand...\nInput: todo/deadline/event");
        }
        return null;
    }
}
