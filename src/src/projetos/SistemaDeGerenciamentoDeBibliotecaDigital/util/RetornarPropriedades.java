package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RetornarPropriedades {

    public static <T> String capturar(T obj, Class clazz) {
        Method metodo = null;
        Object retorno = "";
        String nomeMetodo = "";
        Field[] propriedades = clazz.getDeclaredFields();
        try {
            for (Field f : propriedades) {
                nomeMetodo = "get" +  capitalize(f.getName());
                metodo = clazz.getMethod(nomeMetodo);
                retorno = metodo.invoke(obj);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return retorno.toString();
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
