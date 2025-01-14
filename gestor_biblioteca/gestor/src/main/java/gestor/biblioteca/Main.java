package gestor.biblioteca;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
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


    public static String prestecsforatermini(){
        String filePath_Prestecs = "./data/prestecs.json";
        String filePath_Usuaris = "./data/usuaris.json";
        StringBuilder pfora = new StringBuilder();

        Integer IdLlibre = 10;
        Integer DataDevolucio = 15;
        Integer IdUsuari = 10;
        int Nomusuari = 30;

        pfora.append(String.format("%-" + IdLlibre + "s %-"+ IdUsuari +"s %-"+ DataDevolucio + "s %-" + Nomusuari +"s%n", "IdLlibre", "IdUsuari","DataDevolucio","Nomusuari"));
        pfora.append("-------------------------------------------------------------------------\n");

        try {
            String datosusuari = new String(Files.readAllBytes(Paths.get(filePath_Usuaris)));
            String datosprestec = new String(Files.readAllBytes(Paths.get(filePath_Prestecs)));

            JSONArray jsonArray = new JSONArray(datosusuari);
            JSONArray jsonArraypres = new JSONArray(datosprestec);

            LocalDate currenDate = LocalDate.now();
            DateTimeFormatter fortime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (int i = 0; i < JSONArray.length(); i++){
                JSONObject prestamos = jsonArray.getJSONObject(i);
                String idenLlibre = jsonObject.getInt("Idllibre");
                String idenUsuari = JSONObject.getInt("IdUsuari");
            }
        }
        
    }

    /*public static String llibresPerAutorLlistat(String autor) {
        
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
    }*/
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        /*System.out.println(prestecsLlistas());*/
        String listaPrestecs = Main.prestecsLlistas();
        /*String autorlistado = Main.llibresPerAutorLlistat();*/

        System.out.println(listaPrestecs);
        /*System.out.println(autorlistado);*/

    }
}