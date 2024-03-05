public class PhoneCall extends Event {
    private int number;

    public PhoneCall(String name, EventTime time, int number) {
        super(name, time);
        this.number = number;
    }

    public PhoneCall(String name, int hourOfStart, int minuteOfStart, int hourOfEnd, int minuteOfEnd, int number) {
        super(name, hourOfStart, minuteOfStart, hourOfEnd, minuteOfEnd);
        this.number = number;
    }

    public int getNumberOfGuests() {
        return number;
    }

    @Override
    public String toString() {
        return super.toString() + " | number: " + number;
    }
}