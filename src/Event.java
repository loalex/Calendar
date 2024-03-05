import java.io.Serializable;
import java.time.LocalTime;

public abstract class Event implements Serializable {
    private final String name;
    private final EventTime eventTime;

    public Event(String name, EventTime time) {
        this.name = name;
        this.eventTime = time;
    }

    public Event(String name, int hourOfStart, int minuteOfStart, int hourOfEnd, int minuteOfEnd) {
        this(name, new EventTime(hourOfStart, minuteOfStart, hourOfEnd, minuteOfEnd));
    }

    public String getName() {
        return name;
    }

    public LocalTime getTimeOfStart() {
        return eventTime.getTimeStart();
    }

    public LocalTime getTimeOfEnd() {
        return eventTime.getTimeEnd();
    }

    @Override
    public String toString() {
        return "[ " + name + " ] " + getTimeOfStart() + " -> " + getTimeOfEnd();
    }
}