package gestor.biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class afegirUsuari {
    
     public static void afegirUsuari() {
        String filePathUsuaris = "./data/usuaris.json";
        Scanner scanner = new Scanner(System.in);

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));
            JSONArray jsonArray = new JSONArray(contenido);

            int maxID = 10;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject usuarisexistentes = jsonArray.getJSONObject(i);
                int currentId = usuarisexistentes.getInt("id");
                if (currentId > maxID) {
                    maxID = currentId;
                }
            }
            int newID = maxID + 1;

            System.out.print("Introduce el nombre del usuario: ");
            String nom = scanner.nextLine();

            System.out.print("Escribe los apellidos del usuario: ");
            String cognoms = scanner.nextLine();

            /* No permitir añadir usuarios ya existentes */
            boolean duplicado = false;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject usuariosexistentes = jsonArray.getJSONObject(i);
                if (usuariosexistentes.getString("nom").equalsIgnoreCase(nom) && usuariosexistentes.getString("cognoms").equalsIgnoreCase(cognoms)) {
                    duplicado = true;
                    break;
                }
            }
            if (duplicado) {
                System.out.println("Error: Ya existe un usuario con la información proporcionada.");
            } else {
                System.out.print("Introduce el numero de telefono: ");
                String telefon = scanner.nextLine().trim();
            
                JSONObject newUser = new JSONObject();
                newUser.put("id", newID);
                newUser.put("nom", nom);
                newUser.put("cognoms", cognoms);
                newUser.put("telefon", telefon);

                jsonArray.put(newUser);

                 try (FileWriter file = new FileWriter(filePathUsuaris)) {
                file.write(jsonArray.toString(4));
                }
            
                System.out.println("Nuevo usuario añadido con éxito: ID = " + newID + ", Nom = " + nom + " " + cognoms + ", Telefon = " + telefon);
            }

        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperad: " + e.getMessage());
        } finally{
            scanner.close();
        }
        
    }

}
