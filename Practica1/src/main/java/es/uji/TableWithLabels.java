package es.uji;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TableWithLabels extends Table {
    public Map<String, Integer> lebelsToIndex(String fichero) throws FileNotFoundException {
        Map<String, Integer> Etiquetas = new HashMap<>();
        Scanner sc = new Scanner(new File(fichero));
        sc.nextLine();
        int ClaseEtiqueta = 0;
        while (sc.hasNextLine()) {
            String[] valores = sc.nextLine().split(",");
            if (Etiquetas.containsKey(valores[valores.length - 1])) {
                Etiquetas.put(valores[valores.length - 1], ClaseEtiqueta);
            } else {
                Etiquetas.put(valores[valores.length - 1], ClaseEtiqueta++);
            }
        }
        sc.close();
        return Etiquetas;
    }
    public void addFilaConEtiqueta(String[] linea, Map<String,Integer> Etiquetas) {
        RowWithLabels FilaConEtiqueta = new RowWithLabels();
        for (int i=0; i<linea.length-1; i++) {
            FilaConEtiqueta.data.add(Double.valueOf(linea[i]));
        }
        FilaConEtiqueta.numberClass = Etiquetas.get(linea[linea.length-1]);
        datos.add(FilaConEtiqueta);
    }
    public RowWithLabels getRowAt(int rowNumber) {
        return (RowWithLabels) super.getRowAt(rowNumber);
    }
}
