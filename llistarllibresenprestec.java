package gestor.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class llistarllibresenprestec{

    public static String prestecsLlista(){
        String filePathPrestecs = "./data/prestecs.json";
        String filePathLlibres = "./data/llibres.json";
        StringBuilder prestecList = new StringBuilder();

        int idLlibreWidth = 10;
        int nomLlibreWith = 30;
        int estatWidth = 15;

        prestecList.append(String.format("%-" + idLlibreWidth + "s %-" + nomLlibreWith + "s %-" + estatWidth + "s%n", "IdLlibre", "NomLlibre", "Estat"));
        prestecList.append("------------------------------------------------------------\n");

        try {
            String prestecsContent = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));
            String llibresContent = new String(Files.readAllBytes(Paths.get(filePathLlibres)));

            JSONArray prestecsArray = new JSONArray(prestecsContent);
            JSONArray llibresArray = new JSONArray(llibresContent);

            /*Recorrer para mostrar el estado de los libros*/
            for (int i = 0; i < llibresArray.length(); i++) {
                JSONObject llibre = llibresArray.getJSONObject(i);
                int idLlibre = llibre.getInt("IdLlibre");
                String nomLlibre = llibre.getString("NomLlibre");
                String estat = "Disponible";

                /*Buscar si el libro esta en prestamo */
                for (int j = 0; j < prestecsArray.length(); j++){
                    JSONObject prestec = prestecsArray.getJSONObject(j);
                    if (prestec.getInt("IdLlibre") == idLlibre) {
                        String dataDevolucio = prestec.getString("DataDevolucio");
                        estat = dataDevolucio.isEmpty() ? "En Prestamo" : "Disponible";
                        break;
                    }
                }
            
                prestecList.append(String.format("%-" + idLlibreWidth + "d %-" + nomLlibreWith + "s %-" + estatWidth + "s%n", idLlibre, nomLlibre, estat));
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return prestecList.toString();
    }

    public static void main(String[] args) {
        
        System.out.println(prestecsLlista());
    }

}