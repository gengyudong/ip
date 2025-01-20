public class Events extends Task {
    protected String startDatetime;
    protected String endDatetime;

    public Events(String description, String startDatetime, String endDatetime) {
        super(description);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDatetime + " to: " + this.endDatetime +")";
    }
}
