package gestor.biblioteca;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John");
        jsonObject.put("age", 30);
        jsonArray.put(jsonObject);
        System.out.println(jsonArray.toString());
    }
}