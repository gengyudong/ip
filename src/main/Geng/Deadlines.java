import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime deadline;

    public Deadlines(String description, String deadline) throws GengException {
        super(description);
        this.deadline = parseDateTime(deadline);
    }

    private LocalDateTime parseDateTime(String dateTime) throws GengException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new GengException("Invalid date/time format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "D |" + super.toString() + " | " + this.deadline.format(formatter);
    }
}
