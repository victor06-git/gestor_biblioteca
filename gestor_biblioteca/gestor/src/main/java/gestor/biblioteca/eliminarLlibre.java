package gestor.biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class eliminarLlibre {
    
    public static void eliminarLLibre(){
        String filePathLlibres = "./data/llibres.json";
        Scanner scanner = new Scanner(System.in);

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            JSONArray jsonArray = new JSONArray(contenido);

            System.out.print("Introduce el titulo del libro a eliminar: ");
            String titol = scanner.nextLine().trim();

            System.out.print("Introduce el autor del libro: ");
            String autor = scanner.nextLine().trim();

            boolean found = false;
            int indexToRemove = -1;

            /* Buscar libro pot titulo y autor */
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject librosExistentes = jsonArray.getJSONObject(i);
                String tituloExistente = librosExistentes.getString("titol").trim();
                String autorExistente = librosExistentes.getString("autor").trim();

                if (tituloExistente.equals(titol) && autorExistente.equals(autor)){
                    System.out.print("¿Estas seguro que quieres eliminar el libro " + titol + " de " + autor + "? (s/n): ");
                    String confirmar = scanner.nextLine().trim().toLowerCase();

                    if (confirmar.equals("s")){
                        jsonArray.remove(i);
                        found = true;

                        try (FileWriter file = new FileWriter(filePathLlibres)) {
                            file.write(jsonArray.toString(4));
                        }
                        System.out.println("Libro eliminado con éxito.");

                    } else {
                        System.out.println("Operación cancelada");
                    }
                    break;
                }
            }
            
            if (!found) {
                System.out.println("No se encontró un libro con los datos proporcionados.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
