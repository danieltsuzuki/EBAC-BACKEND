package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public static void main(String[] args) throws Exception {
        Object objeto = instanciarComConstrutor(Objetos.class, List.of(2, 3, 1));
        Field campo = objeto.getClass().getDeclaredField("lista");
        campo.setAccessible(true);
        List<Integer> listaAtual = (List<Integer>) campo.get(objeto);
        List<Integer> listaMutavel = new ArrayList<>(listaAtual);

        // Adicionar elementos
        listaMutavel.add(99);
        listaMutavel.add(100);

        // Definir a nova lista no campo
        campo.set(objeto, listaMutavel);

        for (Integer num : (List<Integer>) campo.get(objeto)) {
            System.out.println(num);
        }
    }

    private static <T> T instanciarComConstrutor(Class<T> clazz, Object... parametros) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> c : clazz.getConstructors()) {
            Class<?>[] tipos = c.getParameterTypes();
            if (tipos.length == parametros.length) {
                boolean compativel = true;
                for (int i = 0; i < tipos.length; i++) {
                    if (!tipos[i].isAssignableFrom(parametros[i].getClass())) {
                        compativel = false;
                        break;
                    }
                }
                if (compativel) {
                    return (T) c.newInstance(parametros);
                }
            }
        }
        throw new IllegalArgumentException("Nenhum construtor compatível encontrado.");
    }
}
