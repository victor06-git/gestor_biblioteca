import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class eliminarUsuario {
    
     public static void eliminarUsuario() {
        String filePathUsuarios = "./data/usuaris.json";
        Scanner scanner = new Scanner(System.in);

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(filePathUsuarios)));
            JSONArray jsonArray = new JSONArray(contenido);

            System.out.print("Escribe el nombre del usuario a eliminar: ");
            String nom = scanner.nextLine().trim();

            System.out.print("Escribe los apellidos del usuario a eliminar: ");
            String cognoms = scanner.nextLine().trim();

            boolean found = false;

            /* Buscar por nombre y apellido */
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject usuariosexistentes = jsonArray.getJSONObject(i);
                if (usuariosexistentes.getString("nom").equalsIgnoreCase(nom) && usuariosexistentes.getString("cognoms").equalsIgnoreCase(cognoms)) {

                    System.out.print("¿Estas seguros que quieres eliminar el usuario " + nom + " " + cognoms + "? (s/n): ");
                    String confirmacion = scanner.nextLine().trim().toLowerCase();

                    if (confirmacion.equals("s")) {
                        jsonArray.remove(i);
                        found = true;

                        try (FileWriter file = new FileWriter(filePathUsuarios)) {
                            file.write(jsonArray.toString(4));
                        }
                        System.out.println("Usuario eliminado con éxito.");
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("No existe un usuario asociado con la información proporcionada.");
            }

        }catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
