package geng.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import geng.ui.GengException;

public class EventsTest {

    @Test
    public void testEventCreation() throws GengException {
        Events event = new Events("Meeting", "2023-10-01 1000", "2023-10-01 1200");
        assertEquals("Meeting", event.getDescription());
        assertEquals(LocalDateTime.parse("2023-10-01T10:00"), event.getStartDatetime());
        assertEquals(LocalDateTime.parse("2023-10-01T12:00"), event.getEndDatetime());
    }

    @Test
    public void testEventToString() throws GengException {
        Events event = new Events("Meeting", "2023-10-01 1000", "2023-10-01 1200");
        assertEquals("E | 0 | Meeting | 01 Oct 2023 10:00 am - 01 Oct 2023 12:00 pm", event.toString());
    }
}
