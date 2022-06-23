import java.io.*;
import java.util.List;

public class Main {

    // Очень хотелось иправить слово Inav но я пересилил себя.

    public static void main(String[] args) {
        List<Employee> list;
        String json;

        Task1 t1 = new Task1();
        Task2 t2 = new Task2();
        Task3 t3 = new Task3();

        list = t1.parseCSV(t1.getColumnMapping(), t1.getFilename());
        json = t1.listToJson(list);
        writeString(json, t1.getJsonFilename());

        list = t2.parseXML("data.xml");
        json = t1.listToJson(list);
        writeString(json, t2.JSON_FILENAME);

        json = readString(t3.jsonFileForTask3);
        list = t3.jsonToList(json);

        list.forEach(System.out::println);
    }

    private static void writeString(String json, String jsonFilename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilename))) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readString(String s) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
}
