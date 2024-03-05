import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Events {
    public void sortEvent(Map<LocalDate, List<Event>> events) {
        Integer i = 0;
        for (Map.Entry<LocalDate, List<Event>> entry : events.entrySet()) {
            for (Event event : entry.getValue()) {
                if (i < 3) {
                    System.out.println(event);
                    i++;
                }
            }
        }
    }
}