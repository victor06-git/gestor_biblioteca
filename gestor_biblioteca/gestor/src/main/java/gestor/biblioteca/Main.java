package gestor.biblioteca;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    
    /**
     * Funció que llistar els llibres de la biblioteca que tinguin el titol corresponent.
     * 
     * @param title Serveix per aconseguir el titol del llibre que es vol buscar.
     * @return Retorna un array de llibres que tinguin el titol corresponent.
     */
    public static String llibresNomLlistat(String title) {
        // Function to get the list of books from the JSON file by name
        String filePath_books = "./data/llibres.json";
        
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

    /**
     * Funció que llistar els usuaris de la biblioteca usuaris (.json) en una llista.
     * 
     * @return Retorna un array d'usuaris segons el Nom, Cognom i Telèfon.
     */
    public static String usuarisLlistat() {
        // Function to get the list of users from the JSON file
        String filePath_users = "./data/usuaris.json";
        StringBuilder userList = new StringBuilder(); 

        int nomWidth = 15;
        int cognomsWidth = 20;
        int phoneWidth = 15;

        userList.append(String.format("%-" + nomWidth + "s %-"
                        + cognomsWidth + "s %-" + phoneWidth + "s%n", "Nom", "Cognoms", "Telèfon"));
        userList.append("----------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_users)));

            JSONArray jsonArray = new JSONArray(content); //Convertir el contingut a JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String user = jsonObject.getString("nom");
                String surname = jsonObject.getString("cognoms");
                String telephone = jsonObject.getString("telefon");

                userList.append(String.format("%-" + nomWidth + "s %-"
                                + cognomsWidth + "s %-" + phoneWidth + "s%n", user, surname, telephone));
            }
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
        }
        return userList.toString();
    }

    /**
     * Funció que llistar els prestecs de la biblioteca prestecs (.json) en una llista.
     * 
     * @return Retorna un array de prestecs segons el "IdLlibre", "IdUsuari", "DataPrestec" i "DataDevolucio".
     */
    public static String prestecsLlista() {
        // Function to get the list of books from the JSON file
        String filePath_prestecs = "./data/prestecs.json";
        StringBuilder prestecList = new StringBuilder();

        int idLlibreWidth = 10;
        int idUsuariWidth = 10;
        int dataPrestecWidth = 15;
        int dataDevolucioWidth = 15;

        prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + 
                        idUsuariWidth + "s %-" + dataPrestecWidth + "s %-" +
                        dataDevolucioWidth + "s%n", "IdLlibre", "IdUsuari", "Data Prestec", "Data Devolucio"));
        prestecList.append("----------------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));

            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Integer idLlibre = jsonObject.getInt("IdLlibre");
                Integer idUsuari = jsonObject.getInt("IdUsuari");
                String dataPrestec = jsonObject.getString("DataPrestec");
                String dataDevolucio = jsonObject.getString("DataDevolucio");

                prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + idUsuariWidth + "s %-" +
                                    dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n", idLlibre, idUsuari, dataPrestec, dataDevolucio));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }
        return prestecList.toString();
    }

    /* Función que lista los libros en préstamo y los libros disponibles.
     * 
     * @return retorna un array de todos los libros y dice cuales estan en prestamo y cuales estan disponibles.
     * Muestra el idLlibre, NomLlibre, estado.
     */

    public static void LlibresprestecsLlistat(){
        String filePathPrestecs = "./data/prestecs.json";
        String filePathLlibres = "./data/llibres.json";
        StringBuilder prestecList = new StringBuilder();

        int idLlibreWidth = 10;
        int nomLlibreWith = 40;
        int estatWidth = 15;

        prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + nomLlibreWith + "s %-" + estatWidth + "s%n", "IdLlibre", "NomLlibre", "estat"));
        prestecList.append("------------------------------------------------------------\n");

        try {
            String prestecsContent = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            String llibresContent = new String(Files.readAllBytes(Paths.get(filePathLlibres)));

            JSONArray prestecsArray = new JSONArray(prestecsContent);
            JSONArray llibresArray = new JSONArray(llibresContent);

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            /*Recorrer para mostrar el estado de los libros*/
            for (int i = 0; i < llibresArray.length(); i++) {
                JSONObject llibre = llibresArray.getJSONObject(i);

                int id = llibre.getInt("id");
                String nomLlibre = llibre.getString("titol");
                String estat = "Disponible";

                /*Buscar si el libro esta en prestamo */
                for (int j = 0; j < prestecsArray.length(); j++){
                    JSONObject prestec = prestecsArray.getJSONObject(j);
                    if (prestec.getInt("IdLlibre") == id) {
                        String dataDevolucioStr = prestec.optString("DataDevolucio", " ");
                        if (!dataDevolucioStr.isEmpty()) {
                            LocalDate dataDevolucio = LocalDate.parse(dataDevolucioStr, formatter);
                            if (dataDevolucio.isAfter(currentDate)){
                                estat = "En Prestamo";
                            }
                        }
                        break;
                    }
                }
            
                prestecList.append(String.format("%-" + idLlibreWidth + "d %-" + nomLlibreWith + "s %-" + estatWidth + "s%n", id, nomLlibre, estat));
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(prestecList.toString());
    }

    /* Función que lista los libros por el autor 
     * 
     * @return el idLlibre, titol, autor.
     */
    public static void llibresperautorllistat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del autor (o presione Enter para listar todos): ");
        String autor = scanner.nextLine();
        if (autor == null) {
            autor = "";
        }
        autor = autor.trim().toLowerCase();

        String filePathllibre = "./data/llibres.json";
        StringBuilder llibresAutor = new StringBuilder();

        int idLlibreWidth = 5;
        int nomLlibreWidth = 30;
        int autorLlibreWidth = 25;

        llibresAutor.append(String.format("%-" + idLlibreWidth + "s %-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n","id","titol","autor"));
        llibresAutor.append("------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePathllibre)));
            JSONArray jsonArray = new JSONArray(content);

            HashMap<String, StringBuilder> autoresLibros = new HashMap<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idllibre = jsonObject.getInt("id");
                String titolllibre = jsonObject.getString("titol");
                String autorNom = jsonObject.getString("autor");

                if (autor.isEmpty() || autorNom.toLowerCase().contains(autor.toLowerCase())) {

                    autoresLibros
                        .computeIfAbsent(autorNom, k -> new StringBuilder())
                        .append(String.format("%-" + idLlibreWidth + "d %-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", idllibre , titolllibre, autorNom));        
                }

            }

            if (autoresLibros.isEmpty()) {
                System.out.println("No se encontraron libros para el autor: " + autor);
                return;
            }

            for (HashMap.Entry<String, StringBuilder> entry : autoresLibros.entrySet()) {
                llibresAutor.append("autor: ").append(entry.getKey()).append("\n");
                llibresAutor.append(entry.getValue()).append("\n");
            }

            System.out.println(llibresAutor.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* Función que lista los préstamos fuera de termino, en los que los libros no han sido devueltos
     * 
     * @return retorna un lista de IdUsuario, IdLlibre, DataPrestec, DataDevolucio.
     * 
     */
    public static void prestecsforatermini(){
        String filePathPrestecs = "./data/prestecs.json";
        StringBuilder prestecsList = new StringBuilder();

        int idUsuariWidth = 10;
        int idLlibreWidth = 10;
        int dataPrestecWidth = 15;
        int dataDevolucioWidth = 15;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        prestecsList.append(String.format("%-" + idUsuariWidth + "s %-" + idLlibreWidth +  "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n", "IdUsuari", "IdLlibre", "DataPrestec", "DataDevolucio"));
        prestecsList.append("--------------------------------------------------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            JSONArray jsonArray = new JSONArray(content);

            HashMap<String, StringBuilder> usersLoans = new HashMap<>();
            LocalDate currentDate = LocalDate.now();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idUsuari = jsonObject.getInt("IdUsuari");
                int idLlibre = jsonObject.getInt("IdLlibre");
                String DataPrestec = jsonObject.getString("DataPrestec");
                String DataDevolucio = jsonObject.optString("DataDevolucio", "");

                LocalDate prestecDate = LocalDate.parse(DataPrestec, formatter);
                LocalDate devolucioDate = DataDevolucio.isEmpty() ? null : LocalDate.parse(DataDevolucio, formatter);

                /*Verificar estado de prestamo */
                if (devolucioDate == null || devolucioDate.isAfter(currentDate)) {
                    StringBuilder loans = usersLoans.computeIfAbsent(String.valueOf(idUsuari), k -> new StringBuilder());
                    loans.append(String.format("%-" + idUsuariWidth + "s %-" + idLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n",
                    idUsuari, idLlibre, DataPrestec, DataDevolucio.isEmpty() ? "No devuelto" : DataDevolucio));
                }
            }

            if (usersLoans.isEmpty()) {
                System.out.println("No se encontraron prestamos fuera de termino.");
                return;
            }

            for (HashMap.Entry<String, StringBuilder> entry : usersLoans.entrySet()) {
                prestecsList.append("Usuario: ").append(entry.getKey()).append("\n");
                prestecsList.append(entry.getValue()).append("\n");
            }

            System.out.println(prestecsList.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        /*System.out.println(prestecsLlistas());*/
        /*String listaPrestecs = Main.prestecsLlistas();*/
        /*String llistatllibresperautor = Main.llibresperautorllistat("");*/
       /*  prestecsforatermini();*/
       /* llibresperautorllistat();*/
       LlibresprestecsLlistat();

        /*System.out.println(listaPrestecs);*/
       /*  System.out.println(llistatllibresperautor);*/
       /* System.out.println(foraterminilist);
       /*  System.out.println(afegirusuario);*/
        /*System.out.println(autorlistado);*/
        /*afegirUsuari();*/
        /*eliminarUsuario();*/
        /*modificarLLibre();*/
        /* eliminarLLibre(); */


    }
}