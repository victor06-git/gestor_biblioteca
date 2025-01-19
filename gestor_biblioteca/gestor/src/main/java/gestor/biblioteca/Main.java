package gestor.biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    //Funcions per els menús
    public static void menuPrincipal(){
        //MENÚ PRINCIPAL
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de biblioteca");
            System.out.println("1. Llibres");
            System.out.println("2. Usuaris");
            System.out.println("3. Prèstecs");
            System.out.println("0. Sortir");
            System.out.print("\nEscull una opció: ");
            String opcio = scanner.nextLine().trim().toLowerCase(); //convertim l'opció a lowerCase per a que no hi hagin conflictes

            switch (opcio) {
                case "1":
                case "llibres":
                    System.out.println("\nHas seleccionat 'Llibres'.");
                    menuLlibres();
                    break;

                case "2":
                case "usuaris":
                    System.out.println("\nHas seleccionat 'Usuaris'.");
                    menuUsuaris();
                    break;

                case "3":
                case "préstecs":
                    System.out.println("\nHas seleccionat 'Préstecs'.");
                    menuPrestecs();
                    break;

                case "0":
                case "sortir":
                    sortir = true;
                    System.out.println("\nSortint del programa...");
                    break;
            
                default:
                    System.out.println("\nOpció no vàlida. Torna-ho a intentar.\n");
                    break;
            }
        }
        scanner.close();
    }


    public static void menuUsuaris(){
        //MENÚ USUARIS
        Scanner scanner = new Scanner(System.in);
        boolean tornar = false;

        while (!tornar) {
            System.out.println("Gestió d'usuaris");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("0. Tornar");
            System.out.print("\nEscull una opció: ");
            String opcio = scanner.nextLine().trim().toLowerCase(); //convertim l'opció a lowerCase per a que no hi hagin conflictes

            switch (opcio) {
                case "1":
                case "afegir":
                    System.out.println("\nHas seleccionat 'Afegir'");
                    afegirUsuari();
                    break;
                
                case "2":
                case "modificar":
                    System.out.println("\nHas seleccionat 'Modificar'");
                    modificar_usuaris();
                    break;

                case "3":
                case "eliminar":
                    System.out.println("\nHas seleccionat 'Eliminar'");
                    //aquí s'ha d'afegir la funció que elimina usuaris
                    break;

                case "4":
                case "llistar":
                    menuLlistarUsuaris();
                    break;
                
                case "0":
                case "tornar":
                    tornar = true;
                    break;

                default:
                    System.out.println("\nOpció no vàlida. Torna-ho a intentar.\n");
                    break;
            }

        }
    }

    public static void menuLlistarUsuaris(){
        //MENÚ LLISTAR USUARIS
        Scanner scanner = new Scanner(System.in);
        boolean tornar = false;

        while (!tornar) {
            System.out.println("Llistar usuaris");
            System.out.println("1. Tots");
            System.out.println("2. Amb préstecs actius");
            System.out.println("3. Amb préstecs fora de termini");
            System.out.println("0. Tornar al menú d'usuaris");
            System.out.print("\nEscull una opció: ");
            String opcio = scanner.nextLine().trim().toLowerCase(); //convertim l'opció a lowerCase per a que no hi hagin conflictes

            switch (opcio) {
                case "1":
                case "tots":
                    System.out.println("\nHas seleccionat 'Tots'");
                    System.out.println(usuarisLlistat());
                    break;
                
                case "2":
                case "amb préstecs actius":
                case "amb prestecs actius":
                case "préstecs actius":
                case "prestecs actius":
                case "actius":
                    System.out.println("\nHas seleccionat 'Amb préstecs actius'");
                    System.out.println(usuarisLlistatAmbPrestecsActius());
                    break;
                
                case "3":
                case "amb préstecs fora de termini":
                case "amb prestecs fora de termini":
                case "préstecs fora de termini":
                case "prestecs fora de termini":
                case "fora de termini":
                    System.out.println("\nHas seleccionat 'Amb préstecs fora de termini'");
                    System.out.println(usuarisLlistatAmbPrestecsForaTermini());
                    break;

                case "0":
                case "tornar":
                    tornar = true;
                    break;

                default:
                    System.out.println("nOpció no vàlida. Torna-ho a intentar.\n");
                    break;
            }
        }
    }

    /* Menú de Libros */
    public static void menuLlibres() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú de Llibres");
            System.out.println("1. Afegir");
            System.out.println("2. Modififcar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("5. Torna al menú principal");
            System.out.print("Selecciona una opció: ");

            int opció = scanner.nextInt();
            scanner.nextLine();

            switch (opció) {
                case 1:
                    System.out.println("Has seleccionat afegir llibre");
                    afegir_llibres();
                    break;
                case 2:
                    System.out.println("Has seleccionat modificar llibre");
                    modificarLLibre();
                    break;
                case 3:
                    System.out.println("Has seleccionat eliminar llibre");
                    eliminarLLibre();
                    break;
                case 4:
                    System.out.println("Has seleccionat llistar llibres");
                    menuLlistarLlibres();
                    break;
                case 5:
                    System.out.println("Tornant al menú principal...");
                    salir = true;
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna a intentar-ho");
            }
        }
    }

    /*MENÚ DE LISTAR LIBROS */
    public static void menuLlistarLlibres() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú de llistar llibres");
            System.out.println("1. Tots els llibres");
            System.out.println("2. Llibres en préstec");
            System.out.println("3. Llibres per autor");
            System.out.println("4. Cercar títol");
            System.out.println("5. Torna al menú de llibres");
            System.out.print("Selecciona una opció: ");

            int opció = scanner.nextInt();
            scanner.nextLine();

            switch (opció) {
                case 1:
                    System.out.println("Llistar tots els llibres.");
                    llibresLlistat();
                    break;
                case 2:
                    System.out.println("Llistar els llibres en préstec.");
                    /* Añadir la función de listar los libros en prestamo */
                    break;
                case 3: 
                    System.out.println("Llistar els llibres per autor.");
                    llibresperautorllistat();
                    break;
                case 4:
                    System.out.println("Llistar els llibres per títol.");
                    /* Funcion de buscar por título */
                    break;
                case 5:
                    System.out.println("Tornant al menú llibres...");
                    menuLlibres();
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna a intentar-ho");
            }
        }
    }

    public static void menuPrestecs() {
        Scanner scanner = new Scanner(System.in);   
        boolean salir = false;

        while (!salir) {
            System.out.println("Gestió de préstecs");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("0. Tornar");
            System.out.print("\nEscull una opció: ");
            String opcio = scanner.nextLine().trim().toLowerCase(); //convertim l'opció a lowerCase per a que no hi hagin conflictes

            switch (opcio) {
                case "1":
                case "afegir":
                    System.out.println("\nHas seleccionat 'Afegir'");
                    afegir_prestecs();
                    break;

                case "2":
                case "modificar":
                    System.out.println("\nHas seleccionat 'Modificar'");
                    modificar_prestecs();
                    break;

                case "3":
                case "eliminar":
                    System.out.println("\nHas seleccionat 'Eliminar'");
                    eliminar_prestecs();
                    break;

                case "4":
                case "llistar":
                    System.out.println("\nHas seleccionat 'Llistar'");
                    //aquí s'ha d'afegir la funció del menú que llista els préstecs
                    menuLlistarPrestecs();
                    break;
                
                case "0":
                case "tornar":
                    salir = true;
                    menuPrincipal();
                    break;
                
                default:
                    System.out.println("\nOpció no vàlida. Torna-ho a intentar.\n");
                    break;
            }
        }
    }

    public static void menuLlistarPrestecs() {
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Llistar préstecs");
            System.out.println("1. Tots");
            System.out.println("2. Actius");
            System.out.println("3. Fora de termini");
            System.out.println("0. Tornar al menú de préstecs");
            System.out.print("\nEscull una opció: ");
            String opcio = scanner.nextLine().trim().toLowerCase(); //convertim l'opció a lowerCase per a que no hi hagin conflictes

            switch (opcio) {
                case "1":
                case "tots":
                    System.out.println("\nHas seleccionat 'Tots'");
                    System.out.println(prestecsLlista());
                    break;
                
                case "2":
                case "actius":
                    System.out.println("\nHas seleccionat 'Actius'");
                    System.out.println(llistatPrestecsActius());
                    break;
                
                case "3":
                case "fora de termini":
                    System.out.println("\nHas seleccionat 'Fora de termini'");
                    System.out.println();
                    break;

                case "0":
                case "tornar":
                    sortir = true;
                    menuPrestecs();
                    break;

                default:
                    System.out.println("nOpció no vàlida. Torna-ho a intentar.\n");
                    break;
            }
        }
    }





    //Funcions per afegir, modificar i eliminar llibres, usuaris i prestecs
public static void afegir_llibres() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Escriu el títol del llibre a afegir: ");
    String titol = scanner.nextLine();
    System.out.print("Escriu l'autor del llibre a afegir: ");
    String autor = scanner.nextLine();

    JSONObject nouLlibre = new JSONObject();
    nouLlibre.put("titol", titol);
    nouLlibre.put("autor", autor);

    try {
        String filePath_llibres = "./data/llibres.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath_llibres)));
        JSONArray llibres = new JSONArray(content);

        int nouId = 1;
        for (int i = 0; i < llibres.length(); i++) {
            JSONObject llibre = llibres.getJSONObject(i);
            Integer id = llibre.getInt("id");

            if (id == nouId) {
                nouId++;
                i = -1; // Tornem a començar el bucle per comprovar si l'ID està repetit o no
            }
        }
        nouLlibre.put("id", nouId);

        llibres.put(nouLlibre);
        Files.write(Paths.get(filePath_llibres), llibres.toString(4).getBytes());

        // Formato tabular para imprimir
        int idWidth = 10;
        int titolWidth = 30;
        int autorWidth = 25;

        System.out.println();
        System.out.println(String.format("%-" + idWidth + "s  %-" + titolWidth + "s  %-" + autorWidth + "s", "ID", "Títol", "Autor"));
        System.out.println("--------------------------------------------------------------");
        System.out.println(String.format("%-" + idWidth + "d  %-" + titolWidth + "s  %-" + autorWidth + "s", nouId, titol, autor));

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
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

    /*FUNCIÓN AÑADIR USUARIO */
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

    public static void modificar_usuaris() {
        //modificar usuaris
        Scanner scanner = new Scanner(System.in);
        String filePathUsuaris = "./data/usuaris.json";
        StringBuilder userList = new StringBuilder();

        int nomWidth = 15;
        int cognomsWidth = 20;
        int phoneWidth = 15;
        userList.append(String.format("%-" + nomWidth + "s %-"
                + cognomsWidth + "s %-" + phoneWidth + "s%n", "Nom", "Cognoms", "Telèfon"));
                userList.append("----------------------------------------------\n");

        try{
            String contentUsuaris = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));
            JSONArray usuarisArray = new JSONArray(contentUsuaris);

            System.out.print("Escriu l'ID del usuari a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for(int i = 0; i < usuarisArray.length();i++){
                JSONObject usuari = usuarisArray.getJSONObject(i);
                Integer idUsuari = usuari.getInt("id");
                String nom = usuari.getString("nom");
                String cognoms = usuari.getString("cognoms");
                String telefon = usuari.getString("telefon");

                if (id == idUsuari) {
                    System.out.println("L'usuari amb ID " + id + " conté les següents dades: ");
                    userList.append(String.format("%-" + nomWidth + "s %-"
                            + cognomsWidth + "s %-" + phoneWidth + "s%n", nom, cognoms, telefon));
                    System.out.println(userList.toString());

                    boolean continuar = true;
                    while (continuar) {
                        System.out.println("Quin camp vols modificar?: ");
                        System.out.println("1. Nom");
                        System.out.println("2. Cognoms");
                        System.out.println("3. Telèfon");
                        System.out.println("4. Tornar");
                        System.out.print("Selecciona una opció: ");
                        String opcio = scanner.nextLine();
                        scanner.nextLine(); // cnsumir el salt de linia restant

                        switch (opcio) {
                            case "1"://validem nom
                            case "nom":
                                boolean nomValid = false;
                                while (!nomValid) {
                                    System.out.print("\nEscriu el nou nom (només lletres i espais): ");
                                    String nouNom = scanner.nextLine();
                            
                                    if (nouNom.matches("[a-zA-ZÀ-ÿ\\s]+")) { //lletres i espais de aA-zZ, de À-ÿ, incloent espais \\s
                                        usuari.put("nom", nouNom);
                                        System.out.println("Nom actualitzat!\n");
                                        nomValid = true;
                                        nom = nouNom;
                            
                                        Files.write(Paths.get(filePathUsuaris), usuarisArray.toString(4).getBytes());

                                        userList.setLength(0); //netejem el StringBuilder per no escriure el mateix d'abans


                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", "Nom", "Cognoms", "Telèfon"));
                                        userList.append("----------------------------------------------\n");

                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", nom, cognoms, telefon));
                                        System.out.println(userList.toString());

                                    } else {
                                        System.out.println("\nError: El nom només pot contenir lletres i espais. Torna-ho a intentar.\n");
                                    }
                                }
                                break;
                            
                            case "2"://validem cognoms
                            case "cognoms":
                            case "cognom":
                                boolean cognomsValids = false;
                                while (!cognomsValids) {
                                    System.out.print("\nEscriu els nous cognoms (només lletres i espais): ");
                                    String nousCognoms = scanner.nextLine();
                            
                                    if (nousCognoms.matches("[a-zA-ZÀ-ÿ\\s]+")) { //lletres i espais de aA-zZ, de À-ÿ, incloent espais \\s
                                        usuari.put("cognoms", nousCognoms);
                                        System.out.println("Cognoms actualitzats!\n");
                                        cognomsValids = true;
                                        cognoms = nousCognoms;
                            
                                        Files.write(Paths.get(filePathUsuaris), usuarisArray.toString(4).getBytes());

                                        userList.setLength(0); //netejem el StringBuilder per no escriure el mateix d'abans

                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", "Nom", "Cognoms", "Telèfon"));
                                        userList.append("----------------------------------------------\n");

                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", nom, cognoms, telefon));
                                        System.out.println(userList.toString());

                                    } else {
                                        System.out.println("\nError: Els cognoms només poden contenir lletres i espais. Torna-ho a intentar.\n");
                                    }
                                }
                                break;

                            case "3": //validem telèfon"
                            case "telèfon":
                            case "telefon":
                                boolean telefonoValido = false;
                                while (!telefonoValido) {
                                    System.out.print("\nEscriu el nou telèfon (9 dígits): ");
                                    String nouTelefon = scanner.nextLine();
                            
                                    if (nouTelefon.length() == 9 && nouTelefon.chars().allMatch(Character::isDigit)) {
                                        usuari.put("telefon", nouTelefon);
                                        System.out.println("Telèfon actualitzat!\n");
                                        telefonoValido = true;
                                        telefon = nouTelefon;
                            
                                        Files.write(Paths.get(filePathUsuaris), usuarisArray.toString(4).getBytes());

                                        userList.setLength(0); //netejem el StringBuilder per no escriure el mateix d'abans

                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", "Nom", "Cognoms", "Telèfon"));
                                        userList.append("----------------------------------------------\n");
                                        
                                        userList.append(String.format("%-" + nomWidth + "s %-"
                                            + cognomsWidth + "s %-" + phoneWidth + "s%n", nom, cognoms, telefon));
                                        System.out.println(userList.toString());

                                    } else {
                                        System.out.println("\nError: El telèfon ha de contenir exactament 9 dígits numèrics. Torna-ho a intentar.\n");
                                    }
                                }
                                break;
                            
                            case "4":
                            case "tornar":
                                continuar = false;
                                System.out.println("Sortint del menú de modificació.\n");
                                break;
                            default:
                                System.out.println("\nOpció no vàlida. Intenta-ho de nou.\n");
                                break;
                        }
                    }

                    // Guardar cambios en el archivo JSON
                    Files.write(Paths.get(filePathUsuaris), usuarisArray.toString(4).getBytes());
                    System.out.println("Els canvis s'han guardat correctament.\n");
                    return;
                }
            }

            System.out.println("\nNo s'ha trobat cap usuari amb l'ID especificat.\n");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* Eliminar usuario */
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
                    try {
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
                            
                    } catch (InputMismatchException e) {
                            System.out.println("Introdueix un número vàlid.");
                            scanner.nextLine();
                        }
                }
                    
                    
                
                    boolean idUsuariPrestecs = false; //Variable per a comprovar si l'usuari té més de 4 préstecs
                    //bucleUsuari = false; //Variable per a comprovar si l'usuari existeix o no                                    
                    while (!idUsuariPrestecs) {    
                        try {
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

                        } catch (InputMismatchException ex) {
                                System.out.println("Introdueix un número vàlid.");
                                scanner.nextLine();
                                //bucleUsuari = true;
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
                    String[] dataDevolucioArray = dataDevolucio.split("-");
                    int anyDevolucio = Integer.parseInt(dataDevolucioArray[0]);
                    int mesDevolucio = Integer.parseInt(dataDevolucioArray[1]);
                    int diaDevolucio = Integer.parseInt(dataDevolucioArray[2]);

                    if (mesDevolucio < 1 || mesDevolucio > 12) {
                        System.out.println("El mes ha de ser entre 1 i 12.");
                        continue;
                    }
                    boolean diaValid = false;
                    switch (mesDevolucio) {
                        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                            diaValid = (diaDevolucio >= 1 && diaDevolucio <= 31);
                            break;
                        case 4: case 6: case 9: case 11:
                            diaValid = (diaDevolucio >= 1 && diaDevolucio <= 30);
                            break;
                        case 2:
                            if (anyDevolucio % 4 == 0) {
                                diaValid = (diaDevolucio >= 1 && diaDevolucio <= 29);
                            } else {
                                diaValid = (diaDevolucio >= 1 && diaDevolucio <= 28);
                            }
                            break;
                    }

                    if (diaValid) {
                        dataCorrecta = true;
                    } else {
                        System.out.println("El dia no és vàlid per al mes introduït.");
                    }

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

    public static void modificar_prestecs() {
        Scanner scanner = new Scanner(System.in);
        String filePathPrestecs = "./gestor_biblioteca/gestor/data/prestecs.json";
        String filePathLlibres = "./gestor_biblioteca/gestor/data/llibres.json";
        String filePathUsuaris = "./gestor_biblioteca/gestor/data/usuaris.json";
        StringBuilder prestecList = new StringBuilder();

        int idLlibreWidth = 10;
        int idUsuariWidth = 10;
        int dataPrestecWidth = 15;
        int dataDevolucioWidth = 15;
        prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + idUsuariWidth + "s %-" + 
                                        dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n", "ID Llibre", "ID Usuari", "Data Prestec", "Data Devolucio"));
        prestecList.append("------------------------------------------------------------\n");
        
        try {
            String contentPrestecs = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            String contentLlibres = new String(Files.readAllBytes(Paths.get(filePathLlibres)));
            String contentUsuaris = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));
            JSONArray prestecs = new JSONArray(contentPrestecs);
            JSONArray llibres = new JSONArray(contentLlibres);
            JSONArray usuaris = new JSONArray(contentUsuaris);
            //int idPrestecNou = -1;
            
                System.out.print("Introdueix el ID del préstec que vols modificar: "); //Pregunta a l'usuari quin ID vol modificar
                
                int idPrestec = scanner.nextInt();
                scanner.nextLine();
                
                for (int i = 0; i < prestecs.length(); i++) {
                    JSONObject prestec = prestecs.getJSONObject(i);
                    Integer id = prestec.getInt("Id");


                    if (id == idPrestec) {
                        //idPrestecNou = i;
                        System.out.println("El préstec amb ID " + idPrestec + " conté les següents dades:");
                        prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + idUsuariWidth + "s %-" + 
                                            dataPrestecWidth + "s %-" + dataDevolucioWidth + "s%n", 
                                            prestec.getInt("IdLlibre"), prestec.getInt("IdUsuari"), 
                                            prestec.getString("DataPrestec"), prestec.getString("DataDevolucio")));
                        
                        System.out.println(prestecList.toString());                   
            
                    boolean continuar = true;
                    while (continuar) {
                        //JSONObject prestec = prestecs.getJSONObject(idPrestecNou);
                        System.out.println("Quin camp vols modificar?");
                        System.out.println("1. ID Llibre");
                        System.out.println("2. ID Usuari");
                        System.out.println("3. Data Prestec");
                        System.out.println("4. Data Devolucio");
                        System.out.println("5. Sortir");
                        System.out.print("Selecciona una opció: ");
                        
                        if (scanner.hasNextInt()) {
                            int opcio = scanner.nextInt();
                            scanner.nextLine(); // consumir el salt de línia restant

                            switch (opcio) {
                                case 1: //Modificar ID Llibre
                                boolean idLlibrePrestat = false;
                                while (!idLlibrePrestat) {
                                    int count = 0;
                                    boolean idLlibreTrobat = false;
                                    try {
                                        System.out.print("Introdueix el nou ID del llibre: ");
                                        int nouIdLlibre = scanner.nextInt();
                                
                                        // Iterar sobre llibres
                                        for (int j = 0; j < llibres.length(); j++) {
                                            JSONObject llibre = llibres.getJSONObject(j);
                                            Integer idLlibre = llibre.getInt("id");
                                
                                            if (nouIdLlibre == idLlibre) {
                                                System.out.println("El llibre amb ID " + nouIdLlibre + " és: " + llibre.getString("titol"));
                                                idLlibreTrobat = true;
                                
                                                // Comprobar si el llibre està prestat
                                                for (int k = 0; k < prestecs.length(); k++) {
                                                    JSONObject llibre2 = prestecs.getJSONObject(k);
                                                    Integer idLlibre2 = llibre2.getInt("IdLlibre");
                                
                                                    if (nouIdLlibre == idLlibre2) {
                                                        count++;
                                                    }
                                                }
                                
                                                if (count > 0) {
                                                    System.out.println("El llibre amb ID " + nouIdLlibre + " està prestat.");
                                                } else {
                                                    System.out.println("El llibre amb ID " + nouIdLlibre + " no està prestat.");
                                                    prestec.put("IdLlibre", nouIdLlibre);
                                                    System.out.println("ID Llibre actualitzat!\n");
                                                    idLlibrePrestat = true;
                                                }
                                                break; // Salir del bucle una vez que se encuentra el libro
                                            }
                                        }
                                
                                        // Si no se encontró el libro, imprimir el mensaje de error
                                        if (!idLlibreTrobat) {
                                            System.out.println("No existeix cap llibre amb l'ID " + nouIdLlibre);
                                        }
                                
                                    } catch (InputMismatchException e) {
                                        System.out.println("Introdueix un número vàlid.");
                                        scanner.nextLine(); // Limpiar el buffer del scanner
                                    }
                                }
                                        break;
                                
                                case 2: // Modificar ID Usuari
                                
                                    boolean idUsuariPrestecs = false; //Variable per a comprovar si l'usuari té més de 4 préstecs                                    
                                    while (!idUsuariPrestecs) {    
                                        try {
                                            boolean idUsuariTrobat = false; //Variable per a comprovar si l'usuari existeix o no   
                                            int countUsuari = 0; //Variable per a comptar els préstecs de l'usuari
                                            System.out.print("Escriu la nova ID de l'usuari del prestec: ");
                                            int nouIdUsuari = scanner.nextInt();

                                            for (int j = 0; j < usuaris.length(); j++) { //Bucle per a recorrer el json d'usuaris
                                                JSONObject usuari = usuaris.getJSONObject(j);
                                                Integer idUsuari2 = usuari.getInt("id");

                                                if (nouIdUsuari == idUsuari2) {
                                                    System.out.println("L'usuari amb ID " + nouIdUsuari + " és: " + usuari.getString("nom") + " " + usuari.getString("cognoms"));
                                                    idUsuariTrobat = true;
                                                    break;
                                                } else {
                                                    idUsuariTrobat = false;
                                                }
                                            }
                                            
                                            if (!idUsuariTrobat) {
                                                System.out.println("No existeix cap usuari amb l'ID " + nouIdUsuari);
                                                continue;
                                            }
                                                
                                            
                                            for (int k = 0; k < prestecs.length(); k++) {
                                                JSONObject prestec2 = prestecs.getJSONObject(k);
                                                Integer idUsuari3 = prestec2.getInt("IdUsuari");  

                                                if (nouIdUsuari == idUsuari3) {
                                                        countUsuari++;
                                                    }
                                            }

                                            if (countUsuari == 4) {
                                                System.out.println("L'usuari amb ID " + nouIdUsuari + " té 4 préstecs.");
                                                    
                                            } else  if (countUsuari < 4) {
                                                System.out.println("L'usuari amb ID " + nouIdUsuari + " té " + countUsuari + " préstecs.");
                                                prestec.put("IdUsuari", nouIdUsuari);
                                                System.out.println("ID Usuari actualitzat!\n");
                                                break;
                                                }

                                        } catch (InputMismatchException ex) {
                                                System.out.println("Introdueix un número vàlid.");
                                                scanner.nextLine();
                                            } 
                                        }                                
                                    break;

                                case 3: //Modificar Data Prestec
                                    Date dataActual = new Date();                  
                                    boolean dataCorrecta = false; //Variable per a comprovar si la data de devolució és correcta
                                    while(!dataCorrecta) {   
                                        System.out.print("Introdueix la nova data de prestec (yyyy-MM-dd): ");
                                        String dataPrestec = scanner.next();
                        
                                    try {    
                                        
                                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                        Date dataPrestec2 = formato.parse(dataPrestec);

                                        if (dataPrestec2.after(dataActual)) {
                                            String[] dataPrestecArray = dataPrestec.split("-");
                                            int anyPrestec = Integer.parseInt(dataPrestecArray[0]);
                                            int mesPrestec = Integer.parseInt(dataPrestecArray[1]);
                                            int diaPrestec = Integer.parseInt(dataPrestecArray[2]);
                        
                                            if (mesPrestec < 1 || mesPrestec > 12) {
                                                System.out.println("El mes ha de ser entre 1 i 12.");
                                                continue;
                                            }
                                            boolean diaValid = false;
                                            switch (mesPrestec) {
                                                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                                                    diaValid = (diaPrestec >= 1 && diaPrestec <= 31);
                                                    break;
                                                case 4: case 6: case 9: case 11:
                                                    diaValid = (diaPrestec >= 1 && diaPrestec <= 30);
                                                    break;
                                                case 2:
                                                    if (anyPrestec % 4 == 0) {
                                                        diaValid = (diaPrestec >= 1 && diaPrestec <= 29);
                                                    } else {
                                                        diaValid = (diaPrestec >= 1 && diaPrestec <= 28);
                                                    }
                                                    break;
                                            }
                        
                                            if (diaValid) {
                                                dataCorrecta = true;
                                                prestec.put("DataPrestec", dataPrestec);
                                                System.out.println("Data de prestec actualitzada!\n");
                                            } else {
                                                System.out.println("El dia no és vàlid per al mes introduït.");
                                            }
                                        } else {
                                            System.out.println("La data de prestec ha de ser posterior a la data actual.");
                                        }

                                    } catch (ParseException e) {

                                        System.out.println("Format de data incorrecta. Si us plau, introdueix la data en format yyyy-MM-dd.");
                                        }
                                    }
                                            
                                    break;

                                case 4: //Modificar Data Devolucio
                                    Date dataActual2 = new Date();
                                    String dataActual_2 = new SimpleDateFormat("yyyy-MM-dd").format(dataActual2); //Data actual en format String per afegir-ho al prestec.json
                        
                                    System.out.println("Data actual: " + dataActual_2);
                        
                                    boolean dataCorrecta2 = false; //Variable per a comprovar si la data de devolució és correcta
                                    while(!dataCorrecta2) {   
                                        System.out.print("Escriu la data de devolució (yyyy-MM-dd): ");
                                        String dataDevolucio = scanner.nextLine();
                        
                                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                    try {
                                        Date dataDevolucioDate = formato.parse(dataDevolucio); //Conversió de la data a format Date
                                        
                                        if (dataDevolucioDate.after(dataActual2)) {
                                            String[] dataDevolucioArray = dataDevolucio.split("-");
                                            int anyDevolucio = Integer.parseInt(dataDevolucioArray[0]);
                                            int mesDevolucio = Integer.parseInt(dataDevolucioArray[1]);
                                            int diaDevolucio = Integer.parseInt(dataDevolucioArray[2]);
                        
                                            if (mesDevolucio < 1 || mesDevolucio > 12) {
                                                System.out.println("El mes ha de ser entre 1 i 12.");
                                                continue;
                                            }
                                            boolean diaValid = false;
                                            switch (mesDevolucio) {
                                                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                                                    diaValid = (diaDevolucio >= 1 && diaDevolucio <= 31);
                                                    break;
                                                case 4: case 6: case 9: case 11:
                                                    diaValid = (diaDevolucio >= 1 && diaDevolucio <= 30);
                                                    break;
                                                case 2:
                                                    if (anyDevolucio % 4 == 0) {
                                                        diaValid = (diaDevolucio >= 1 && diaDevolucio <= 29);
                                                    } else {
                                                        diaValid = (diaDevolucio >= 1 && diaDevolucio <= 28);
                                                    }
                                                    break;
                                            }
                        
                                            if (diaValid) {
                                                dataCorrecta2 = true;
                                                prestec.put("DataDevolucio", dataDevolucio);
                                                System.out.println("Data de devolució actualitzada!\n"); 

                                            } else {
                                                System.out.println("El dia no és vàlid per al mes introduït.");
                                            }
                        
                                        } else {
                                            System.out.println("La data de devolució ha de ser posterior a la data actual.");
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Format de data incorrecta. Si us plau, introdueix la data en format yyyy-MM-dd.");
                                        }
                                    }

                                    break;

                                    case 5: // Sortir
                                        continuar = false;
                                        System.out.println("Sortint del menú...");
                                        break;
                                    
                                    default:
                                        System.out.println("Opció no vàlida.");
                                        break;
                                    }
                            
                            } else {

                                String opcio = scanner.nextLine();

                                switch (opcio) {
                                    case "IdLlibre": case "idLlibre": case "idllibre": case "Idllibre": //Modificar ID Llibre
                                    boolean idLlibrePrestat = false;
                                    while (!idLlibrePrestat) {
                                        int count = 0;
                                        boolean idLlibreTrobat = false;
                                        try {
                                            System.out.print("Introdueix el nou ID del llibre: ");
                                            int nouIdLlibre = scanner.nextInt();
                                    
                                            // Iterar sobre llibres
                                            for (int j = 0; j < llibres.length(); j++) {
                                                JSONObject llibre = llibres.getJSONObject(j);
                                                Integer idLlibre = llibre.getInt("id");
                                    
                                                if (nouIdLlibre == idLlibre) {
                                                    System.out.println("El llibre amb ID " + nouIdLlibre + " és: " + llibre.getString("titol"));
                                                    idLlibreTrobat = true;
                                    
                                                    // Comprobar si el llibre està prestat
                                                    for (int k = 0; k < prestecs.length(); k++) {
                                                        JSONObject llibre2 = prestecs.getJSONObject(k);
                                                        Integer idLlibre2 = llibre2.getInt("IdLlibre");
                                    
                                                        if (nouIdLlibre == idLlibre2) {
                                                            count++;
                                                        }
                                                    }
                                    
                                                    if (count > 0) {
                                                        System.out.println("El llibre amb ID " + nouIdLlibre + " està prestat.");
                                                    } else {
                                                        System.out.println("El llibre amb ID " + nouIdLlibre + " no està prestat.");
                                                        prestec.put("IdLlibre", nouIdLlibre);
                                                        System.out.println("ID Llibre actualitzat!\n");
                                                        idLlibrePrestat = true;
                                                    }
                                                    break; // Salir del bucle una vez que se encuentra el libro
                                                }
                                            }
                                    
                                            // Si no se encontró el libro, imprimir el mensaje de error
                                            if (!idLlibreTrobat) {
                                                System.out.println("No existeix cap llibre amb l'ID " + nouIdLlibre);
                                            }
                                    
                                        } catch (InputMismatchException e) {
                                            System.out.println("Introdueix un número vàlid.");
                                            scanner.nextLine(); // Limpiar el buffer del scanner
                                        }
                                    }
                                            break;
                                    
                                    case "IdUsuari": case "idUsuari": case "idusuari": case "Idusuari": // Modificar ID Usuari
                                    
                                        boolean idUsuariPrestecs = false; //Variable per a comprovar si l'usuari té més de 4 préstecs                                    
                                        while (!idUsuariPrestecs) {    
                                            try {
                                                boolean idUsuariTrobat = false; //Variable per a comprovar si l'usuari existeix o no   
                                                int countUsuari = 0; //Variable per a comptar els préstecs de l'usuari
                                                System.out.print("Escriu la nova ID de l'usuari del prestec: ");
                                                int nouIdUsuari = scanner.nextInt();

                                                for (int j = 0; j < usuaris.length(); j++) { //Bucle per a recorrer el json d'usuaris
                                                    JSONObject usuari = usuaris.getJSONObject(j);
                                                    Integer idUsuari2 = usuari.getInt("id");

                                                    if (nouIdUsuari == idUsuari2) {
                                                        System.out.println("L'usuari amb ID " + nouIdUsuari + " és: " + usuari.getString("nom") + " " + usuari.getString("cognoms"));
                                                        idUsuariTrobat = true;
                                                        break;
                                                    } else {
                                                        idUsuariTrobat = false;
                                                    }
                                                }
                                                
                                                if (!idUsuariTrobat) {
                                                    System.out.println("No existeix cap usuari amb l'ID " + nouIdUsuari);
                                                    continue;
                                                }
                                                    
                                                
                                                for (int k = 0; k < prestecs.length(); k++) {
                                                    JSONObject prestec2 = prestecs.getJSONObject(k);
                                                    Integer idUsuari3 = prestec2.getInt("IdUsuari");  

                                                    if (nouIdUsuari == idUsuari3) {
                                                            countUsuari++;
                                                        }
                                                }

                                                if (countUsuari == 4) {
                                                    System.out.println("L'usuari amb ID " + nouIdUsuari + " té 4 préstecs.");
                                                        
                                                } else  if (countUsuari < 4) {
                                                    System.out.println("L'usuari amb ID " + nouIdUsuari + " té " + countUsuari + " préstecs.");
                                                    prestec.put("IdUsuari", nouIdUsuari);
                                                    System.out.println("ID Usuari actualitzat!\n");
                                                    break;
                                                    }

                                            } catch (InputMismatchException ex) {
                                                    System.out.println("Introdueix un número vàlid.");
                                                    scanner.nextLine();
                                                } 
                                            }                                
                                        break;

                                    case "DataPrestec": case "dataprestec": case "dataPrestec": case "Dataprestec": //Modificar Data Prestec
                                        Date dataActual = new Date();                  
                                        boolean dataCorrecta = false; //Variable per a comprovar si la data de devolució és correcta
                                        while(!dataCorrecta) {   
                                            System.out.print("Introdueix la nova data de prestec (yyyy-MM-dd): ");
                                            String dataPrestec = scanner.next();
                            
                                        try {    
                                            
                                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                            Date dataPrestec2 = formato.parse(dataPrestec);

                                            if (dataPrestec2.after(dataActual)) {
                                                String[] dataPrestecArray = dataPrestec.split("-");
                                                int anyPrestec = Integer.parseInt(dataPrestecArray[0]);
                                                int mesPrestec = Integer.parseInt(dataPrestecArray[1]);
                                                int diaPrestec = Integer.parseInt(dataPrestecArray[2]);
                            
                                                if (mesPrestec < 1 || mesPrestec > 12) {
                                                    System.out.println("El mes ha de ser entre 1 i 12.");
                                                    continue;
                                                }
                                                boolean diaValid = false;
                                                switch (mesPrestec) {
                                                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                                                        diaValid = (diaPrestec >= 1 && diaPrestec <= 31);
                                                        break;
                                                    case 4: case 6: case 9: case 11:
                                                        diaValid = (diaPrestec >= 1 && diaPrestec <= 30);
                                                        break;
                                                    case 2:
                                                        if (anyPrestec % 4 == 0) {
                                                            diaValid = (diaPrestec >= 1 && diaPrestec <= 29);
                                                        } else {
                                                            diaValid = (diaPrestec >= 1 && diaPrestec <= 28);
                                                        }
                                                        break;
                                                }
                            
                                                if (diaValid) {
                                                    dataCorrecta = true;
                                                    prestec.put("DataPrestec", dataPrestec);
                                                    System.out.println("Data de prestec actualitzada!\n");
                                                } else {
                                                    System.out.println("El dia no és vàlid per al mes introduït.");
                                                }
                                            } else {
                                                System.out.println("La data de prestec ha de ser posterior a la data actual.");
                                            }

                                        } catch (ParseException e) {

                                            System.out.println("Format de data incorrecta. Si us plau, introdueix la data en format yyyy-MM-dd.");
                                            }
                                        }
                                                
                                        break;

                                    case "DataDevolucio": case "datadevolucio": case "dataDevolucio": case "Datadevolucio": //Modificar Data Devolucio
                                        Date dataActual2 = new Date();
                                        String dataActual_2 = new SimpleDateFormat("yyyy-MM-dd").format(dataActual2); //Data actual en format String per afegir-ho al prestec.json
                            
                                        System.out.println("Data actual: " + dataActual_2);
                            
                                        boolean dataCorrecta2 = false; //Variable per a comprovar si la data de devolució és correcta
                                        while(!dataCorrecta2) {   
                                            System.out.print("Escriu la data de devolució (yyyy-MM-dd): ");
                                            String dataDevolucio = scanner.nextLine();
                            
                                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                        try {
                                            Date dataDevolucioDate = formato.parse(dataDevolucio); //Conversió de la data a format Date
                                            
                                            if (dataDevolucioDate.after(dataActual2)) {
                                                String[] dataDevolucioArray = dataDevolucio.split("-");
                                                int anyDevolucio = Integer.parseInt(dataDevolucioArray[0]);
                                                int mesDevolucio = Integer.parseInt(dataDevolucioArray[1]);
                                                int diaDevolucio = Integer.parseInt(dataDevolucioArray[2]);
                            
                                                if (mesDevolucio < 1 || mesDevolucio > 12) {
                                                    System.out.println("El mes ha de ser entre 1 i 12.");
                                                    continue;
                                                }
                                                boolean diaValid = false;
                                                switch (mesDevolucio) {
                                                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                                                        diaValid = (diaDevolucio >= 1 && diaDevolucio <= 31);
                                                        break;
                                                    case 4: case 6: case 9: case 11:
                                                        diaValid = (diaDevolucio >= 1 && diaDevolucio <= 30);
                                                        break;
                                                    case 2:
                                                        if (anyDevolucio % 4 == 0) {
                                                            diaValid = (diaDevolucio >= 1 && diaDevolucio <= 29);
                                                        } else {
                                                            diaValid = (diaDevolucio >= 1 && diaDevolucio <= 28);
                                                        }
                                                        break;
                                                }
                            
                                                if (diaValid) {
                                                    dataCorrecta2 = true;
                                                    prestec.put("DataDevolucio", dataDevolucio);
                                                    System.out.println("Data de devolució actualitzada!\n"); 

                                                } else {
                                                    System.out.println("El dia no és vàlid per al mes introduït.");
                                                }
                            
                                            } else {
                                                System.out.println("La data de devolució ha de ser posterior a la data actual.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Format de data incorrecta. Si us plau, introdueix la data en format yyyy-MM-dd.");
                                            }
                                        }

                                        break;
                                    
                                    case "Sortir": case "sortir": case "s": case "S":
                                        continuar = false;
                                        System.out.println("Sortint del menú...");
                                        break;
                                    
                                    default:
                                        System.out.println("Opció no vàlida.");
                                        break;
                                    }
                                }
                            }

                        Files.write(Paths.get(filePathPrestecs), prestecs.toString(4).getBytes());
                        System.out.println("Els canvis s'han guardat correctament.\n");
                        return;
                    }
                    System.out.println("\nNo s'ha trobat cap prestec amb l'ID especificat.\n");
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

    /* Función que lista los libros por el autor 
     * 
     * @return retorna un array de llibres basant-se en el seu autor, mostra el idLlibre, titol, autor.
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
        menuLlistarPrestecs();
    }
}