import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Task3 {

    public final String jsonFileForTask3 = "new_data.json";
    public List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        json = json.replace("\n", "");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray array = (JSONArray) jsonObject.get("employees");
            for (Object o :
                    array) {
                list.add(gson.fromJson(o.toString(), Employee.class));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
