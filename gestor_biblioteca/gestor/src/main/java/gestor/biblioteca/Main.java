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

    public static String prestecsLlistas(){
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

        return prestecList.toString();
    }

    public static String llibresperautorllistat(String autor) {
        
        String filePathllibre = "./data/llibres.json";
        StringBuilder llibresAutor = new StringBuilder();

        int nomLlibreWidth = 25;
        int autorLlibreWidth = 25;

        llibresAutor.append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n","titol","autor"));
        llibresAutor.append("------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePathllibre)));
            JSONArray jsonArray = new JSONArray(content);

            HashMap<String, StringBuilder> autoresLibros = new HashMap<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String titolllibre = jsonObject.getString("titol");
                String autorNom = jsonObject.getString("autor");

                if (autorNom.toLowerCase().contains(autor.toLowerCase())) {

                    autoresLibros
                        .computeIfAbsent(autorNom, k -> new StringBuilder())
                        .append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", titolllibre, autorNom));        
                }

            }

            if (autoresLibros.isEmpty()) {
                return "No se encontraron libros para el autor: " + autor;
            }

            for (HashMap.Entry<String, StringBuilder> entry : autoresLibros.entrySet()) {
                llibresAutor.append("autor: ").append(entry.getKey()).append("\n");
                llibresAutor.append(entry.getValue());
                llibresAutor.append("\n");
            }

            return llibresAutor.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null; 
    }

    public static String prestecsforatermini(){
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
                return "No se encontraron prestamos fuera de termino.";
            }

            for (HashMap.Entry<String, StringBuilder> entry : usersLoans.entrySet()) {
                prestecsList.append("Usuario: ").append(entry.getKey()).append("\n");
                prestecsList.append(entry.getValue()).append("\n");
            }

            return prestecsList.toString();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    /*public static String afegirusuario(int idUsuari, String nom, String cognoms, String telefon) {
        String filePathUsuarios = "./data/usuaris.json";
       
        if (idUsuari >= 1 && idUsuari <= 10) {
            return "Error: Los Ids del 1 al 10 están reservados y no pueden utilizarse.";
        }

        try{
            String contenido = new String(Files.readAllBytes(Paths.get(filePathUsuarios)));
            JSONArray jsonArray = new JSONArray(contenido);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject usuariosexistentes = jsonArray.getJSONObject(i);
                if (usuariosexistentes.getInt("id") == idUsuari) {
                    return "Error: El usuario con ID " + idUsuari + " ya existe; Escoge otro ID.";
                }
            }

            /* Cear nuevo usuairo */
           /* JSONObject newUser = new JSONObject();
            newUser.put("id", idUsuari);
            newUser.put("nom", nom);
            newUser.put("cognoms", cognoms);
            newUser.put("telefon", telefon);

            jsonArray.put(newUser);*/

            /*Se añade el usuario en el archivo json */
           /*  try (FileWriter file = new FileWriter(filePathUsuarios)) {
                file.write(jsonArray.toString(4));
            }

            return "Nuevo usuario añadido con exito: ID = " + idUsuari + ", Nom = " + nom + " " + cognoms + ", Telefon = " + telefon;

        } catch (IOException e) {
            return "Error al leer o escribir en el archivo: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }*/

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

    public static void modificarLLibre(){
        String filePathLlibres = "./data/llibres.json";
        Scanner scanner = new Scanner(System.in);

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            JSONArray jsonArray = new JSONArray(contenido);

            System.out.print("Escribe el título del libro que quieres modificar: ");
            String titol = scanner.nextLine().trim();

            System.out.print("Escribe el auotr de libro: ");
            String autor = scanner.nextLine().trim();

            boolean found = false;

            /* Buscar libro */
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject librosExistentes = jsonArray.getJSONObject(i);
                String tituloExistente = librosExistentes.getString("titol").trim();
                String autorExistente = librosExistentes.getString("autor").trim();

                
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        /*System.out.println(prestecsLlistas());*/
        /*String listaPrestecs = Main.prestecsLlistas();*/
        /*String llistatllibresperautor = Main.llibresperautorllistat("");*/
       /* String foraterminilist = Main.prestecsforatermini();
        String afegirusuario = Main.afegirusuario(10, , , )*/

        /*System.out.println(listaPrestecs);*/
        /*System.out.println(llistatllibresperautor);*/
        /*System.out.println(foraterminilist);
       /*  System.out.println(afegirusuario);*/
        /*System.out.println(autorlistado);*/
        /*afegirUsuari();*/
        /*eliminarUsuario();*/
        eliminarLLibre();

    }
}