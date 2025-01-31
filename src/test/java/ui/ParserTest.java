package ui;

import duke.ui.Parser;
import duke.commands.Command;
import duke.ui.GengException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_returnsCorrectCommand() {
        try {
            Parser parser = new Parser();
            Command command = parser.parseInput("todo read book");
            assertNotNull(command);
        } catch (GengException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void parse_invalidCommand_throwsDukeException() {
        Parser parser = new Parser();
        assertThrows(GengException.class, () -> parser.parseInput("invalid command"));
    }
}
