package gestor.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;


public class librosautor {
    
    public static String llibresPerAutorLlistat(String autor) {
        // Function to get the list of books from the JSON file by author
        String filePath_books = "./data/llibres.json";
    
        StringBuilder llibresAutor = new StringBuilder();
    
        Integer nomLlibreWidth = 25;
        Integer autorLlibreWidth = 15;
    
        llibresAutor.append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", "Titol", "Autor"));
        llibresAutor.append("--------                  ----------\n");
    
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_books)));
            
            JSONArray jsonArray = new JSONArray(content); // Convertir el contingut a JSONArray
    
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String bookTitle = jsonObject.getString("titol");
                String autorNom = jsonObject.getString("autor");
    
                if (autorNom.toLowerCase().contains(autor.toLowerCase())) {
                    llibresAutor.append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", bookTitle, autorNom));
                }
            }
            return llibresAutor.toString();
    
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        llibresPerAutorLlistat(null);
    }
}  
