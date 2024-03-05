import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Calendar implements Serializable {
    private Map<LocalDate, List<Event>> events = new TreeMap<>();//implementuje sorted map
    Scanner scanner = new Scanner(System.in);

    public Calendar() {
        LocalDate date = LocalDate.now();
        Integer day = date.getDayOfMonth();
        Integer endDay = date.withDayOfMonth(date.getMonth().length(date.isLeapYear())).getDayOfMonth();

        for (int i = day; i <= endDay; i++) {
            events.put(date, new ArrayList<Event>());
            date = date.plusDays(1);
        }
        date = LocalDate.now();
        events.get(date).add(new PhoneCall("Call to Kasia", 8, 7, 8, 17, 123456789));
        events.get(date.plusDays(1)).add(new Meeting("Meet with friends from school", 9, 00, 11, 00));
        events.get(date.plusDays(2)).add(new Meeting("With Kuba", 12, 00, 13, 10));
        events.get(date).add(new Reminder("Learn programing", 19, 00, 23, 00));
        events.get(date).add(new Reminder("Physiotherapist", 14, 00, 15, 00));
        events.get(date).add(new Meeting("With family", 16, 20, 18, 30));
    }

    private int intFromInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    private int whichDay() {
        System.out.println("On which day?");
        return intFromInput();
    }

    private void addEvent() {
        Event newEvent = null;
        System.out.println("What type of event do you want to add?");
        menuOfTypes();
        int type = intFromInput();
        System.out.println("What name?");
        String name = scanner.nextLine();
        System.out.println("What is the start time?");
        int hourOfBegining = intFromInput();
        System.out.println("What minutes of the beginning?");
        int minuteOfStart = intFromInput();
        System.out.println("What hour of the end?");
        int hourOfEnd = intFromInput();
        System.out.println("What minutes of the end?");
        int minuteOfEnd = intFromInput();
        switch (type) {
            case 1:
                newEvent = new Meeting(name, hourOfBegining, minuteOfStart, hourOfEnd, minuteOfEnd);
                break;
            case 2:
                System.out.println("Which number");
                int number = scanner.nextInt();
                newEvent = new PhoneCall(name, hourOfBegining, minuteOfStart, hourOfEnd, minuteOfEnd, number);
                break;
            case 3:
                newEvent = new Reminder(name, hourOfBegining, minuteOfStart, hourOfEnd, minuteOfEnd);
                break;
            default:
                System.out.print("Something went wrong");
        }
        if (newEvent != null) {
            events.get(getDateWithSpecificDay(-1)).add(newEvent);
            System.out.println("Event added");
        }
    }

    private LocalDate getDateWithSpecificDay(int day) {
        LocalDate date = LocalDate.now();
        if (day == -1) {
            date.withDayOfMonth(whichDay());
        } else {
            date.withDayOfMonth(day);
        }
        return date;
    }

    private void removeEvent() {
        List<Event> events = this.events.get(getDateWithSpecificDay(-1));
        System.out.println("Events:");
        events.stream().forEach(System.out::println);
        System.out.println("Choose index of event");
        int index = intFromInput();
        events.remove(index);
        System.out.println("Event removed");
    }

    private void showEvents() {
        System.out.print("Your Events:\n");
        events.get(getDateWithSpecificDay(-1)).stream().forEach(System.out::println);

    }

    private void infoToUserOfSearch() {
        System.out.println("What criteria are you looking for?");
        System.out.println("Type 1 to search for the type");
        System.out.println("Type 2 to search for the day");
        System.out.println("Type 3 to search for the hour");
        System.out.println("Type 4 to search for the name");
        System.out.println("Type 5 if you are done and you would like to see results");
    }

    private void menuToSearchEvents() {
        boolean exe = true;
        String eventName = "";
        int hour = 0;
        int dayNumber = 0;
        int type = 0;
        while (exe) {
            infoToUserOfSearch();
            int check = intFromInput();
            switch (check) {
                case 1:
                    System.out.println("Which type of the event are you looking for?");
                    menuOfTypes();
                    type = intFromInput();
                    break;
                case 2:
                    System.out.println("Which day of the event are you looking for?");
                    dayNumber = intFromInput();
                    break;
                case 3:
                    System.out.println("Which hour of the event are you looking for?");
                    hour = intFromInput();
                    break;
                case 4:
                    System.out.println("Which name of the event are you looking for?");
                    eventName = scanner.nextLine();
                    break;
                case 5:
                    exe = false;
                    break;
            }
        }
        filterEvents(eventName, hour, dayNumber, type);
    }

    private void menuOfTypes() {
        System.out.println("Put event type");
        System.out.println("1- PhoneCall");
        System.out.println("2- Reminder");
        System.out.println("3- Meeting");
    }

    private Class returnOfClass(int type) {
        if (type == 0) {
            return PhoneCall.class;
        } else if (type == 1) {
            return Reminder.class;
        } else if (type == 2) {
            return Meeting.class;
        } else {
            return null;
        }
    }

    private void filterEvents(String eventName, int hour, int dayNumber, int type) {
        if (dayNumber == 0) {
            System.out.println("put the day number");
            dayNumber = intFromInput();
        }
        LocalDate specificDay = getDateWithSpecificDay(dayNumber);
        List<Event> events = this.events.get(specificDay);
        Class returnOfClass = returnOfClass(type);
        List<Event> filterCollect = events.stream()
                .filter(n -> eventName.equals("") || (!eventName.equals("") && n.getName().equals(eventName)))
                .filter(n -> hour == 0 || (hour != 0 && n.getTimeOfStart().getHour() == hour))
                .filter(n -> returnOfClass == null || (returnOfClass != null && returnOfClass.isInstance(n)))
                .collect(Collectors.toList());
        System.out.println("_________________\n");
        if (filterCollect.size() == 0) {
            System.out.println("Nothing found");
        } else {
            filterCollect.forEach(n -> System.out.println(n));
        }
    }

    public void run() {
        boolean exe = false;
        Serializer workFile = new Serializer();
        while (!exe) {
            System.out.println("Welcome!");
            System.out.println("Your events:");
            Events threeevents = new Events();
            threeevents.sortEvent(events);
            System.out.println("Type 1 to add new event");
            System.out.println("Type 2 to remove your event");
            System.out.println("Type 3 to show your events");
            System.out.println("Type 4 to search your event");
            System.out.println("Type 5 to exit\n");
            int check = intFromInput();
            switch (check) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    removeEvent();
                    break;
                case 3:
                    showEvents();
                    break;
                case 4:
                    menuToSearchEvents();
                    break;
                case 5:
                    workFile.serialize(events);
                    System.out.println("GoodBye!");
                    exe = true;
                    break;
                default:
                    System.out.println("something went wrong!");
            }
        }
    }
}