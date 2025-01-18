package gestor.biblioteca;

import java.util.Scanner;

public class menus {
       
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
                    /* Escribir la función de añadir libro */
                    break;
                case 2:
                    System.out.println("Has seleccionat modificar llibre");
                    /*modificarLLibre();*/
                    break;
                case 3:
                    System.out.println("Has seleccionat eliminar llibre");
                    /*eliminarLLibre();*/
                    break;
                case 4:
                    System.out.println("Has seleccionat llistar llibres");
                    menuLlistarLlibres();
                    break;
                case 5:
                    System.out.println("Tornant al menú principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna a intentar-ho");
            }
        }
    }

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
                    /* Listar los libros */
                    break;
                case 2:
                    System.out.println("Llistar els llibres en préstec.");
                    /* Añadir la función de listar los libros en prestamo */
                    break;
                case 3: 
                    System.out.println("Llistar els llibres per autor.");
                    /*llibresperautorllistat("Gabriel Garcia");*/
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
}
