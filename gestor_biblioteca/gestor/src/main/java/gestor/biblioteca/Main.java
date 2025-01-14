package gestor.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

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

        StringBuilder llibresNom = new StringBuilder();

        Integer nomLlibreWidth = 25;
        Integer autorLlibreWidth = 15;
        //Integer space = 5;

        llibresNom.append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", "Titol", "Autor"));
        llibresNom.append("--------                  ----------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_books)));
            
            JSONArray jsonArray = new JSONArray(content); //Convertir el contingut a JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String bookTitle = jsonObject.getString("titol");
                String autorNom = jsonObject.getString("autor");

                
                if (bookTitle.toLowerCase().contains(title.toLowerCase())) {
                    llibresNom.append(String.format("%-" + nomLlibreWidth + "s %-" + autorLlibreWidth + "s%n", bookTitle, autorNom));
                }
            }
            return llibresNom.toString();

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
        String filePath_llibres = "./data/llibres.json";
        String filePath_usuaris = "./data/usuaris.json";
        StringBuilder prestecList = new StringBuilder();

        int idWidth = 5;
        int idLlibreWidth = 35;
        int idUsuariWidth = 25;
        int dataPrestecWidth = 15;
        int dataDevolucioWidth = 15;

        prestecList.append(String.format("%-" + idWidth + "s %-" + idLlibreWidth + "s %-" + 
                        idUsuariWidth + "s %-" + dataPrestecWidth + "s %-" +
                        dataDevolucioWidth + "s%n", "ID","Llibre", "Usuari", "Data Prestec", "Data Devolucio"));
        prestecList.append("--------------------------------------------------------------------------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));
            String content_2 = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
            String content_3 = new String(Files.readAllBytes(Paths.get(filePath_usuaris)));

            JSONArray jsonArray = new JSONArray(content);
            JSONArray jsonArray2 = new JSONArray(content_2);
            JSONArray jsonArray3 = new JSONArray(content_3);

            String idNom = "";
            String idLlibreNom = "";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Integer idLlibre = jsonObject.getInt("IdLlibre");
                Integer idUsuari = jsonObject.getInt("IdUsuari");
                
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                    Integer idLlibre2 = jsonObject2.getInt("id");
                    String titolLlibre = jsonObject2.getString("titol");

                    if (idLlibre == idLlibre2) {
                        idLlibreNom = titolLlibre;
                    }
                }

                for (int k = 0; k < jsonArray3.length(); k++) {
                    JSONObject jsonObject3 = jsonArray3.getJSONObject(k);
                    Integer idUsuari2 = jsonObject3.getInt("id");
                    String nomUsuari = jsonObject3.getString("nom");
                    String cognomUsuari = jsonObject3.getString("cognoms");

                    if (idUsuari == idUsuari2) {
                        idNom = nomUsuari + " " + cognomUsuari;
                    }
                }

                String dataPrestec = jsonObject.getString("DataPrestec");
                String dataDevolucio = jsonObject.getString("DataDevolucio");

                prestecList.append(String.format("%-" + idWidth + "s %-" +  idLlibreWidth + "s %-" + idUsuariWidth + "s %-" +
                                    dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n", idLlibre, idLlibreNom, idNom, dataPrestec, dataDevolucio));
            }       
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }
        return prestecList.toString();
    }

    /**
     * Funció que llistar tots els llibres (.json) de la biblioteca.
     * 
     * @return Retorna un array dels llibres segons id, titol i autor.
     */
    public static String llibresLlistat(){
        // Ruta arxiu "llibres.json"
        String filePath_llibres = "./data/llibres.json";
        StringBuilder llibresList = new StringBuilder();

        int idWidth = 5;
        int titolWidth = 35;
        int autorWidth = 25;

        // Crear les columnes
        llibresList.append(String.format("%-" + idWidth + "s %-"
                        + titolWidth + "s %-" + autorWidth + "s%n", "ID", "Títol", "Autor"));
                        llibresList.append("---------------------------------------------------------------------\n");

        //Iterar sobre cada llibre
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
            JSONArray jsonArray = new JSONArray(content);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String titol = jsonObject.getString("titol");
                String autor = jsonObject.getString("autor");

                llibresList.append(String.format("%-" + idWidth + "s %-"
                + titolWidth + "s %-" + autorWidth + "s%n", id, titol, autor));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }
        return llibresList.toString();
    }

    /**
     * Funció que llistar els préstecs d'un usuari
     *
     * @param idUsuari ID de l'usuari
     * @return Retorna un array dels prestecs de l'usuari segons NomUsuari, CognomUsuari, IdPrestec, IdLlibre, DataPrestec i DataDevolucio.
     */
    public static String llistatPrestecsUsuari(int idUsuari){
        // Ruta arxiu "prestecs.json"
        String filePath_prestecs = "./data/prestecs.json";
        String filePath_usuaris = "./data/usuaris.json";
        StringBuilder prestecsList = new StringBuilder();

        int nomUsuariWidth = 10;
        int cognomsUsuariWidth = 20; 
        int idWidth = 12;
        int idLlibreWidth = 12;
        int dataPrestecWidth = 15;
        int dataDevolucioWith = 15;
        

        // Crear les columnes
        prestecsList.append(String.format("%-" + nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %-" + idWidth + "s %-"
                        + idLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith 
                        + "s%n", "Nom", "Cognoms", "ID Préstec", "ID Llibre", "Data Prestec", "Data Devolució"));
                        prestecsList.append("--------------------------------------------------------------------------------------------\n");

        //Iterar sobre cada prestec
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));
            String content_2 = new String(Files.readAllBytes(Paths.get(filePath_usuaris)));
            JSONArray prestecsArray = new JSONArray(content);
            JSONArray usuarisArray = new JSONArray(content_2);

            String strNom = "";
            String strCognoms = "";


            for(int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                int id = prestec.getInt("Id");
                int idLlibre = prestec.getInt("IdLlibre");
                String dataPrestec = prestec.getString("DataPrestec");
                String dataDevolucio = prestec.getString("DataDevolucio");

                for(int j = 0; j < usuarisArray.length(); j++){
                    JSONObject usuari = usuarisArray.getJSONObject(j);
                    Integer idUsuari2 = usuari.getInt("id");
                    String nom = usuari.getString("nom");
                    String cognoms = usuari.getString("cognoms");

                    if (idUsuari == idUsuari2) {
                        strNom = nom;
                        strCognoms = cognoms; 
                    }
                }

                if (prestec.getInt("IdUsuari") == idUsuari) {
                    prestecsList.append(String.format("%-" + nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %-" + idWidth + "s %-"
                    + idLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith + "s%n", strNom, strCognoms, id, idLlibre, dataPrestec, dataDevolucio));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }

        return prestecsList.toString();
    }

    public static String usuarisLlistatAmbPrestecsActius() {
        String filePath_usuaris = "./data/usuaris.json";
        String filePath_prestecs = "./data/prestecs.json";

        StringBuilder usuarisAmbPrestecs = new StringBuilder();

        int usuarisWidth = 25;
        int nomUsuariWidth = 5;

        usuarisAmbPrestecs.append(String.format("%-" + usuarisWidth + "s%n", "Usuari"));
        usuarisAmbPrestecs.append("-------------------------\n");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath_usuaris)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));

            JSONArray jsonArray = new JSONArray(content);
            JSONArray jsonArray2 = new JSONArray(content2);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Integer idUsuari = jsonObject.getInt("id");
                String nomUsuari = jsonObject.getString("nom");
                String cognomUsuari = jsonObject.getString("cognoms");

                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                    Integer idUsuari2 = jsonObject2.getInt("IdUsuari");
                    String dataDevol = jsonObject2.getString("DataDevolucio");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //Format de la data
                    Date dataDevolDate = sdf.parse(dataDevol); //Conversió de la data a format Date
                    Date dataHoy = new Date();

                    if (idUsuari == idUsuari2 && dataDevolDate.after(dataHoy)) {
                        usuarisAmbPrestecs.append(String.format("%-" +  nomUsuariWidth + "s%n", nomUsuari + " " + cognomUsuari));
                        
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return usuarisAmbPrestecs.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(usuarisLlistatAmbPrestecsActius());
    }
}