import java.io.Serializable;
import java.time.LocalTime;

public class EventTime implements Serializable {
    private int hourOfStart;
    private int minuteOfStart;
    private int hourOfEnd;
    private int minuteOfEnd;

    LocalTime time = LocalTime.of(12, 12);

    public EventTime(int hourOfStart, int minuteOfStart, int hourOfEnd, int minuteOfEnd) {
        this.hourOfStart = hourOfStart;
        this.minuteOfStart = minuteOfStart;
        this.hourOfEnd = hourOfEnd;
        this.minuteOfEnd = minuteOfEnd;
    }

    public LocalTime getTimeStart() {
        time = LocalTime.of(hourOfStart, minuteOfStart);
        return time;
    }

    public LocalTime getTimeEnd() {
        time = LocalTime.of(hourOfEnd, minuteOfEnd);
        return time;
    }
}
