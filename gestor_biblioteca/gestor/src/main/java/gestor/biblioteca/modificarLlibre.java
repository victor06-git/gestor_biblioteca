package gestor.biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class modificarLlibre {
    
    public static void modificarLLibre(){
        String filePathLlibres = "./data/llibres.json";
        Scanner scanner = new Scanner(System.in);

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            JSONArray jsonArray = new JSONArray(contenido);

            System.out.print("Escribe el título del libro que quieres modificar: ");
            String titol = scanner.nextLine().trim();

            System.out.print("Escribe el autor de libro: ");
            String autor = scanner.nextLine().trim();

            boolean found = false;

            /* Buscar libro */
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject librosExistentes = jsonArray.getJSONObject(i);
                String tituloExistente = librosExistentes.getString("titol").trim();
                String autorExistente = librosExistentes.getString("autor").trim();
                
                if (tituloExistente.equalsIgnoreCase(titol) && autorExistente.equalsIgnoreCase(autor)) {
                    found = true;

                    System.out.println("Libro econtrado. Introduce los nuevos datos (no escribas nada para no modificar): ");

                    System.out.print("Nuevo titulo: ");
                    String nuevotitulo = scanner.nextLine().trim();
                    if  (!nuevotitulo.isEmpty()) {
                        librosExistentes.put("titol", nuevotitulo);
                    }

                    System.out.print("Nuevo autor: ");
                    String nuevoAutor = scanner.nextLine().trim();
                    if (!nuevoAutor.isEmpty()) {
                        librosExistentes.put("autor",nuevoAutor);
                    }

                    try (FileWriter file = new FileWriter(filePathLlibres)) {
                        file.write(jsonArray.toString(4));
                    }
                    System.out.println("Libro modificado con éxito.");
                    break;
                }   
            }
            if (!found) {
                System.out.println("No se encontró un libro con los datos proporcionados");
            }

        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
