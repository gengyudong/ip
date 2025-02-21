package geng.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import geng.ui.GengException;

public class DeadlinesTest {

    @Test
    public void testDeadlineCreation() throws GengException {
        Deadlines deadline = new Deadlines("Submit report", "2023-10-01 2359");
        assertEquals("Submit report", deadline.getDescription());
        assertEquals(LocalDateTime.parse("2023-10-01T23:59"), deadline.getDeadline());
    }

    @Test
    public void testDeadlineToString() throws GengException {
        Deadlines deadline = new Deadlines("Submit report", "2023-10-01 2359");
        assertEquals("D | 0 | Submit report | 01 Oct 2023 23:59 pm", deadline.toString());
    }
}
