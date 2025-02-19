package ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import geng.commands.Command;
import geng.ui.GengException;
import geng.ui.Parser;


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
