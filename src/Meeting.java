public class Meeting extends Event {
    public Meeting(String name, EventTime time) {
        super(name, time);
    }

    public Meeting(String name, int hourOfStart, int minuteOfStart, int hourOfEnd, int minuteOfEnd) {
        super(name, hourOfStart, minuteOfStart, hourOfEnd, minuteOfEnd);
    }
}