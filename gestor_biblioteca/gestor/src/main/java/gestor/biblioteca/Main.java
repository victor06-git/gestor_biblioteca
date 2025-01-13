package gestor.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    
    
    public static String llibresNomLlistat(String title) {
        // Function to get the list of books from the JSON file by name
        String filePath_books = "./data/llibres.json";

        Integer nomLlibreWidth = 10;
        Integer autorLlibreWidth = 15;

        StringBuilder llibresNomLlistat = new StringBuilder(String.format("%-" + nomLlibreWidth + "s %-" +
                    autorLlibreWidth + "s%n", "Nom", "Autor"));
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_books)));
            
            JSONArray jsonArray = new JSONArray(content); //Convertir el contingut a JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String bookTitle = jsonObject.getString("titol");

                if (bookTitle.toLowerCase().contains(title.toLowerCase())) {
                    return jsonObject.toString();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        
    }
}