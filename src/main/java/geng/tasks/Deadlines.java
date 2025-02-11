package geng.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import geng.ui.GengException;

/**
 * Represents a task with a deadline.
 * Inherits from Task and stores a description and a specific deadline.
 */
public class Deadlines extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     * The deadline is parsed from the string provided into a LocalDateTime object.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline in string format (yyyy-MM-dd HHmm).
     * @throws GengException If the deadline format is invalid.
     */
    public Deadlines(String description, String deadline) throws GengException {
        super(description);
        this.deadline = parseDateTime(deadline);
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
     * Returns the deadline of this task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of the Deadline task, including the description and formatted deadline.
     * The deadline is formatted as "dd MMM yyyy HH:mm a".
     *
     * @return A string representing the task with its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "D |" + super.toString() + " | " + this.deadline.format(formatter);
    }
}
