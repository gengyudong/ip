package duke.tasks;

import duke.ui.GengException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime startDatetime;
    protected LocalDateTime endDatetime;

    public Events(String description, String startDatetime, String endDatetime) throws GengException {
        super(description);
        this.startDatetime = parseDateTime(startDatetime);
        this.endDatetime = parseDateTime(endDatetime);
    }

    private LocalDateTime parseDateTime(String dateTime) throws GengException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new GengException("Invalid date/time format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    public LocalDateTime getStartDatetime() {
        return this.startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return this.endDatetime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "E |" + super.toString() + " | " + this.startDatetime.format(formatter) + " - " +
                this.endDatetime.format(formatter);
    }
}
