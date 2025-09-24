package genericsSetMap;

import java.util.Collections;
import java.util.List;

public class ListaUtil {
    public static <T extends Comparable<T>> void ordenarExibir(List<T> lista) {
        Collections.sort(lista);
        for (T item : lista)
            System.out.println(item);
    }
}
