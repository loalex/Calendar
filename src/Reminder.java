

public class Reminder extends Event {
    public Reminder(String name, EventTime time, String subject) {
        super(name, time);
    }

    public Reminder(String name, int hourOfStart, int minuteOfStart, int hourOfEnd, int minueOfEnd) {
        super(name, hourOfStart, minuteOfStart, hourOfEnd, minueOfEnd);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
