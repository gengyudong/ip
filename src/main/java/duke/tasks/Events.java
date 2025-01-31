package duke.tasks;

import duke.ui.GengException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with an event, which has a start and end datetime.
 * Inherits from Task and stores a description, start, and end datetime for the event.
 */
public class Events extends Task {
    protected LocalDateTime startDatetime;
    protected LocalDateTime endDatetime;

    /**
     * Constructs an Event task with the specified description, start datetime, and end datetime.
     * The start and end datetimes are parsed from the strings provided into LocalDateTime objects.
     *
     * @param description The description of the event task.
     * @param startDatetime The start datetime of the event in string format (yyyy-MM-dd HHmm).
     * @param endDatetime The end datetime of the event in string format (yyyy-MM-dd HHmm).
     * @throws GengException If the datetime format is invalid.
     */
    public Events(String description, String startDatetime, String endDatetime) throws GengException {
        super(description);
        this.startDatetime = parseDateTime(startDatetime);
        this.endDatetime = parseDateTime(endDatetime);
    }

    /**
     * Parses the date and time from a string into a LocalDateTime object.
     * The expected format is "yyyy-MM-dd HHmm".
     *
     * @param dateTime The date and time string to be parsed.
     * @return The corresponding LocalDateTime object.
     * @throws GengException If the date/time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) throws GengException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new GengException("Invalid date/time format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return The start datetime as a LocalDateTime object.
     */
    public LocalDateTime getStartDatetime() {
        return this.startDatetime;
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return The end datetime as a LocalDateTime object.
     */
    public LocalDateTime getEndDatetime() {
        return this.endDatetime;
    }

    /**
     * Returns a string representation of the Event task, including the description,
     * formatted start datetime, and formatted end datetime.
     * The datetime is formatted as "dd MMM yyyy HH:mm a".
     *
     * @return A string representing the task with its start and end datetimes.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "E |" + super.toString() + " | " + this.startDatetime.format(formatter) + " - " +
                this.endDatetime.format(formatter);
    }
}