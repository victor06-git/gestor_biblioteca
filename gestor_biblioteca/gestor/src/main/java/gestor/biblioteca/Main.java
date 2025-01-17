package gestor.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    //Funcions per afegir, modificar i eliminar llibres, usuaris i prestecs
    public static void afegir_llibres(){
        //afegir nous llibres
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriu el títol del llibre a afegir: ");
        String titol = scanner.nextLine();
        System.out.print("Escriu l'autor del llibre a afegir: ");
        String autor = scanner.nextLine();

        JSONObject nouLlibre = new JSONObject();
        nouLlibre.put("titol", titol);
        nouLlibre.put("autor", autor);

        try{
            String filePath_llibres = "./data/llibres.json";
            String content = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
            JSONArray llibres = new JSONArray(content);

            int nouId = 1;
            for (int i = 0; i < llibres.length(); i++){
                JSONObject llibre = llibres.getJSONObject(i);
                Integer id = llibre.getInt("id");

                if (id == nouId) {
                    nouId++;
                    i = -1; //tornem a començar el bucle per a que es comprovi si l'id está repetit o no
                }
                
            }
            nouLlibre.put("id", nouId);

            llibres.put(nouLlibre);
            
            Files.write(Paths.get(filePath_llibres), llibres.toString(4).getBytes());

            System.out.println();
            System.out.println("S'ha afegit el següent llibre:");
            System.out.println("ID: " + nouId);
            System.out.println("Títol: " + titol);
            System.out.println("Autor: " + autor);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            scanner.close();
        }


    }

    public static void eliminar_prestecs() {
        Scanner scanner = new Scanner(System.in);
        String filePathPrestecs = "./data/prestecs.json";
    
        try {
            String contentPrestecs = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            JSONArray prestecsArray = new JSONArray(contentPrestecs);
    
            // demanem l'ID del préstec a eliminar
            System.out.print("Quin ID de préstec vols eliminar?: ");
            int idPrestecEliminar = scanner.nextInt();
    
            JSONObject prestecAEliminar = null;
            int indexToRemove = -1;
    
            // buscar el prestec a eliminar
            for (int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                if (prestec.getInt("Id") == idPrestecEliminar) {
                    prestecAEliminar = prestec;
                    indexToRemove = i;
                    break;
                }
            }
    
            if (prestecAEliminar != null) {
                // confirmem si l'usuari vol eliminar o no el prestec
                System.out.println("Estàs segur que vols eliminar el següent préstec?");
                System.out.println(prestecAEliminar.toString(4));
                System.out.print("Vols eliminar aquest préstec? (s/n): ");
                String confirmacio = scanner.next();
    
                if (confirmacio.equalsIgnoreCase("s")) {
                    // eliminar el préstec i guardar el fitxer
                    prestecsArray.remove(indexToRemove);
                    Files.write(Paths.get(filePathPrestecs), prestecsArray.toString(4).getBytes());
                    System.out.println("S'ha eliminat el préstec amb ID " + idPrestecEliminar);
                } else {
                    System.out.println("No s'ha eliminat el préstec amb ID " + idPrestecEliminar);
                }
            } else {
                System.out.println("No s'ha trobat cap préstec amb l'ID especificat.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void afegir_prestecs() {
        //afegir nous prestecs
        Scanner scanner = new Scanner(System.in);
        
        try {

            String filePathPrestecs = "./data/prestecs.json";
            String filePathLlibres = "./data/llibres.json";
            String filePathUsuaris = "./data/usuaris.json";
            
            String content = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));
            String content3 = new String(Files.readAllBytes(Paths.get(filePathLlibres)));

            JSONArray prestecsArray = new JSONArray(content);       
            JSONArray usuarisArray = new JSONArray(content2);
            JSONArray llibresArray = new JSONArray(content3);

            int idLlibre = -1;
            int idUsuari = -1;
            String dataDevolucio = "";

            int nouId = 1; //Inicialitzem el nou ID a 1
            for (int k = 0; k < prestecsArray.length(); k++){
                JSONObject prestec = prestecsArray.getJSONObject(k);
                Integer id = prestec.getInt("Id");

                if (id == nouId) {
                    nouId++;
                    k = -1; //tornem a començar el bucle per a que es comprovi si l'id está repetit o no
                }    
            }

            boolean idLLibrePrestat = false; //Variable per a comprovar si el llibre està prestat o no
            while (!idLLibrePrestat) {
                boolean idLlibreTrobat = false; //Variable per a comprovar si el llibre existeix o no
                int count = 0; //Variable per a comptar els préstecs del llibre
            
                System.out.print("Escriu l'ID del llibre a prestar: ");
                idLlibre = scanner.nextInt();

                for (int i = 0; i < llibresArray.length(); i++) { //Bucle per a recorrer el json de llibres
                    JSONObject llibre = llibresArray.getJSONObject(i);
                    Integer idLlibre2 = llibre.getInt("id");
                    

                    if (idLlibre == idLlibre2) {
                        System.out.println("El llibre amb ID " + idLlibre + " és: " + llibre.getString("titol"));
                        idLlibreTrobat = true;
                    }
                } 

                    if (!idLlibreTrobat) {
                        System.out.println("No existeix cap llibre amb l'ID " + idLlibre);
                        continue;
                    }
                    
                    for (int j = 0; j < prestecsArray.length(); j++) {
                        JSONObject prestec = prestecsArray.getJSONObject(j);
                        Integer id = prestec.getInt("IdLlibre");

                        if (idLlibre == id) {
                            count++;
                        }
                    }
                        if (count == 1) {
                            System.out.println("El llibre amb ID " + idLlibre + " està prestat.");
                            continue;

                        } else {
                            System.out.println("El llibre amb ID " + idLlibre + " no està prestat.");
                            break;
                        }
                    
                }

            boolean idUsuariPrestecs = false; //Variable per a comprovar si l'usuari té més de 4 préstecs
                                                                            
            while (!idUsuariPrestecs) {    
                boolean idUsuariTrobat = false; //Variable per a comprovar si l'usuari existeix o no   
                int countUsuari = 0; //Variable per a comptar els préstecs de l'usuari
                
                System.out.print("Escriu l'ID de l'usuari del prestec: ");
                idUsuari = scanner.nextInt();

                for (int j = 0; j < usuarisArray.length(); j++) { //Bucle per a recorrer el json d'usuaris
                    JSONObject usuari = usuarisArray.getJSONObject(j);
                    Integer idUsuari2 = usuari.getInt("id");

                    if (idUsuari == idUsuari2) {
                        System.out.println("L'usuari amb ID " + idUsuari + " és: " + usuari.getString("nom") + " " + usuari.getString("cognoms"));
                        idUsuariTrobat = true;
                        break;
                    } else {
                        idUsuariTrobat = false;
                    }
                }
                
                if (!idUsuariTrobat) {
                    System.out.println("No existeix cap usuari amb l'ID " + idUsuari);
                    continue;
                }
                    
                
                for (int k = 0; k < prestecsArray.length(); k++) {
                    JSONObject prestec = prestecsArray.getJSONObject(k);
                    Integer id = prestec.getInt("IdUsuari");  

                       if (idUsuari == id) {
                            countUsuari++;
                        }
                }

                if (countUsuari == 4) {
                    System.out.println("L'usuari amb ID " + idUsuari + " té 4 préstecs.");
                        
                } else  if (countUsuari < 4) {
                    System.out.println("L'usuari amb ID " + idUsuari + " té " + countUsuari + " préstecs.");
                    break;
                    }
            }
            
            
            Date dataActual = new Date();
            String dataPrestec = new SimpleDateFormat("yyyy-MM-dd").format(dataActual); //Data actual en format String per afegir-ho al prestec.json

            System.out.println("Data de prestec: " + dataPrestec);

            boolean dataCorrecta = false; //Variable per a comprovar si la data de devolució és correcta
            while(!dataCorrecta) {   
                System.out.print("Escriu la data de devolució (yyyy-MM-dd): ");
                dataDevolucio = scanner.next();

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dataDevolucioDate = formato.parse(dataDevolucio); //Conversió de la data a format Date
                
                if (dataDevolucioDate.after(dataActual)) {
                    dataCorrecta = true;
                } else {
                    System.out.println("La data de devolució ha de ser posterior a la data actual.");
                }
            } catch (ParseException e) {
                System.out.println("Format de data incorrecta. Si us plau, introdueix la data en format yyyy-MM-dd.");
                }
            }

            
            JSONObject nouPrestec = new JSONObject();
            nouPrestec.put("Id", nouId);
            nouPrestec.put("IdLlibre", idLlibre);
            nouPrestec.put("IdUsuari", idUsuari);
            nouPrestec.put("DataPrestec", dataPrestec);
            nouPrestec.put("DataDevolucio", dataDevolucio);

            

            System.out.println("Vols afegir aquest préstec?");
            System.out.println(nouPrestec.toString(4));
            System.out.print("Vols afegir aquest préstec? (s/n): ");
            String confirmacio = scanner.next();

            JSONArray prestecs = new JSONArray(content);
            
            if (confirmacio.equalsIgnoreCase("s")) {
                prestecs.put(nouPrestec);
                Files.write(Paths.get(filePathPrestecs), prestecs.toString(4).getBytes());
                System.out.println("S'ha afegit el següent préstec:");
                System.out.println("ID: " + nouId);
                System.out.println("ID Llibre: " + idLlibre);
                System.out.println("ID Usuari: " + idUsuari);
                System.out.println("Data de préstec: " + dataPrestec);
                System.out.println("Data de devolució: " + dataDevolucio);
            } else {
                System.out.println("No s'ha afegit el préstec.");
            }
        
        } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
        
    }





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
     * @return Retorna un array dels prestecs de l'usuari segons NomUsuari, CognomUsuari, IdPrestec, IdLlibre, NomLlibre, DataPrestec i DataDevolucio.
     */
    public static String llistatPrestecsUsuari(int idUsuari){

        // Ruta arxiu "prestecs.json"
        String filePath_prestecs = "./data/prestecs.json";
        String filePath_usuaris = "./data/usuaris.json";
        String filePath_llibres = "./data/llibres.json";
        StringBuilder prestecsList = new StringBuilder();

        int nomUsuariWidth = 10;
        int cognomsUsuariWidth = 20; 
        int idWidth = 12;
        int idLlibreWidth = 12;
        int nomLlibreWidth = 30;
        int dataPrestecWidth = 15;
        int dataDevolucioWith = 15;
        

        // Crear les columnes
        prestecsList.append(String.format("%-" + nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %" + idWidth + "s  %" 
        + idLlibreWidth + "s %-" + nomLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith 
        + "s%n", "Nom", "Cognoms", "ID Préstec", "ID Llibre", "Nom Llibre", "Data Prestec", "Data Devolució"));
                        prestecsList.append("--------------------------------------------------------------------------------------------------------------------------\n");

        //Iterar sobre cada prestec
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));
            String content_2 = new String(Files.readAllBytes(Paths.get(filePath_usuaris)));
            String content_3 = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
            JSONArray prestecsArray = new JSONArray(content);
            JSONArray usuarisArray = new JSONArray(content_2);
            JSONArray llibresArray = new JSONArray(content_3);

            String strNom = "";
            String strCognoms = "";
            String strNomLlibre = "";


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

                    for(int k = 0; k < llibresArray.length(); k++){
                        JSONObject llibre = llibresArray.getJSONObject(k);
                        Integer idLlibre2 = llibre.getInt("id");
                        String nomLlibre = llibre.getString("titol");

                        if (idLlibre == idLlibre2) {
                            strNomLlibre = nomLlibre;
                        }
                    }
                }

                if (prestec.getInt("IdUsuari") == idUsuari) {
                    prestecsList.append(String.format("%-" + nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %" + idWidth + "d  %" 
                    + idLlibreWidth + "d  %-" + nomLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith + "s%n", 
                    strNom, strCognoms, id, idLlibre, strNomLlibre, dataPrestec, dataDevolucio));

                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }

        return prestecsList.toString();
    }

    /**
     * Funció que llistar els usuaris amb préstecs actius
     *
     * @return Retorna un array dels usuaris amb préstecs actius segons NomUsuari i CognomUsuari.
     */
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

    /**
     * Funció que llistar els usuaris amb préstecs fora de termini
     *
     * @return Retorna un array dels usuaris amb préstecs fora de termini segons NomUsuari i CognomUsuari.
     */
    public static String usuarisLlistatAmbPrestecsForaTermini() {
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

                    if (idUsuari == idUsuari2 && dataDevolDate.before(dataHoy)) {
                        usuarisAmbPrestecs.append(String.format("%-" +  nomUsuariWidth + "s%n", nomUsuari + " " + cognomUsuari));
                        
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return usuarisAmbPrestecs.toString();
    }



    /**
     * Funció que llista tots els prectes actius
     *
     * @return Retorna un array dels prestecs actius segons id, idUsuari, strNom, strCognoms, idLlibre, strNomLlibre, dataPrestec, dataDevolucio
     */
    public static String llistatPrestecsActius() {
        // Ruta arxiu "prestecs.json"
        String filePath_prestecs = "./data/prestecs.json";
        String filePath_usuaris = "./data/usuaris.json";
        String filePath_llibres = "./data/llibres.json";
        StringBuilder prestecsList = new StringBuilder();

        int idWidth = 10;
        int idUsuariWidth = 10;
        int nomUsuariWidth = 15;
        int cognomsUsuariWidth = 20; 
        int idLlibreWidth = 9;
        int nomLlibreWidth = 30;
        int dataPrestecWidth = 15;
        int dataDevolucioWith = 15;
        

        // Crear les columnes
        prestecsList.append(String.format("%" + idWidth + "s  %-" + idUsuariWidth + "s  %-"+  nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %"
        + idLlibreWidth + "s  %-" + nomLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith 
        + "s%n", "ID Préstec", "Id Usuari", "Nom Usuari", "Cognoms Usuari", "ID Llibre", "Nom Llibre", "Data Prestec", "Data Devolució"));

                        prestecsList.append("--------------------------------------------------------------------------------------------------------------------------------------\n");

        //Iterar sobre cada prestec
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath_prestecs)));
            String content_2 = new String(Files.readAllBytes(Paths.get(filePath_usuaris)));
            String content_3 = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
            JSONArray prestecsArray = new JSONArray(content);
            JSONArray usuarisArray = new JSONArray(content_2);
            JSONArray llibresArray = new JSONArray(content_3);


            LocalDate dataLocalActual = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String strNom = "";
            String strCognoms = "";
            String strNomLlibre = "";



            for(int i = 0; i < prestecsArray.length(); i++) {
                JSONObject prestec = prestecsArray.getJSONObject(i);
                int id = prestec.getInt("Id");
                int idUsuari = prestec.getInt("IdUsuari");
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

                    for(int k = 0; k < llibresArray.length(); k++){
                        JSONObject llibre = llibresArray.getJSONObject(k);
                        Integer idLlibre2 = llibre.getInt("id");
                        String nomLlibre = llibre.getString("titol");

                        if (idLlibre == idLlibre2) {
                            strNomLlibre = nomLlibre;
                        }

                    }
                }
                if (prestec.has("DataDevolucio")) {
                    LocalDate dataDevolucio2 = LocalDate.parse(dataDevolucio, format); //aquesta línea converteix(parse) "dataDevolucio" en un objecte localDate per poder comparar les dates bé

                    if (dataDevolucio2.isAfter(dataLocalActual)) {
                        prestecsList.append(String.format("%" + idWidth + "d  %" + idUsuariWidth + "d  %-" + nomUsuariWidth + "s %-" + cognomsUsuariWidth + "s %"
                        + idLlibreWidth + "d  %-" + nomLlibreWidth + "s %-" + dataPrestecWidth + "s %-" + dataDevolucioWith + "s%n", 
                        id, idUsuari, strNom, strCognoms, idLlibre, strNomLlibre, dataPrestec, dataDevolucio));
                }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            }

        return prestecsList.toString();
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");
        afegir_prestecs();
    }
}