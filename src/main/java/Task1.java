import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Task1 {
    private final String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
    private final String filename = "data.csv";
    private final String jsonFilename = "data.json";

    public String[] getColumnMapping() {
        return columnMapping;
    }

    public String getFilename() {
        return filename;
    }

    public String getJsonFilename() {
        return jsonFilename;
    }

    String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(list, listType);
    }

    //Должен быть пустой конструктор в классе Employee
    List<Employee> parseCSV(String[] columnMapping, String filename) {
        List<Employee> list;
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {

            ColumnPositionMappingStrategy<Employee> ms = new ColumnPositionMappingStrategy<>();
            ms.setColumnMapping(columnMapping);
            ms.setType(Employee.class);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader).withMappingStrategy(ms).build();
            list = csv.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
