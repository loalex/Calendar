import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Serializer {
    public void serialize(Map<LocalDate, List<Event>> events) {

        for (Map.Entry<LocalDate, List<Event>> entry : events.entrySet()) {
            StringBuilder date = new StringBuilder();
            date.append("LocalDate:" + entry.getKey());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"));
                writer.write(date.toString());
                for (Event event : entry.getValue()) {
                    String toString = toString(event);
                    writer.write(toString);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream streamOfBytes = new ByteArrayOutputStream();
        ObjectOutputStream objectToBytes = new ObjectOutputStream(streamOfBytes);
        objectToBytes.writeObject(o);
        objectToBytes.close();
        return Base64.getEncoder().encodeToString(streamOfBytes.toByteArray());
    }

    public Map<LocalDate, List<Event>> deserialize() {
        Map<LocalDate, List<Event>> eventsInfile = new TreeMap<>();
        try {
            BufferedReader readFromFile = new BufferedReader(new FileReader("events.txt"));
            String line;
            while ((line = readFromFile.readLine()) != null) {
                LocalDate date = null;
                if (line.contains("LocalDate")) {
                    String[] split = line.split(":");
                    date = LocalDate.parse(split[split.length - 1]);
                    eventsInfile.put(date, new ArrayList<Event>());
                } else {
                    Event event = (Event) fromString(line);
                    eventsInfile.get(date).add(event);
                }
            }
            readFromFile.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventsInfile;
    }

    private static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}