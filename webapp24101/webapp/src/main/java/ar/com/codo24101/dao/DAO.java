package ar.com.codo24101.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @param <T> parametro Entidad DTO
 */
public interface DAO<T> {

    public void create(T Dto);

    public void update(T Dto);

    public void delete(Long id);

    public ArrayList<T> getLista();

    public ArrayList<T> getByVal(String columnas, String valores, String metodo);

    //// default ////
    default T getByID(Long id) {
        ArrayList<T> l = getByVal("id_movie", id.toString());
        if (!l.isEmpty()) {
            return (T) l.get(0);
        }
        return null;
    };
    
    default ArrayList<T> getByVal(String columna, String filtro) {
        return getByVal(columna, filtro.replaceAll(" ", "%"), "");
    }

    default Boolean validaValores(String valores, Integer cantidadColumnas) {
        String[] listaValores = valores.split(",");

        if (valores.length() == 0 || listaValores.length == 0) {
            System.out.println("""
                               No se pasaron valores para busqueda. los valores deben estar sepados por coma (\",\").
                               Formato de ejemplo de lista de Valores: \"val1,val2,val3,...valN\".""");
            return false;
        } else if (listaValores.length != cantidadColumnas) {
            System.out.println("""
                               La cantidad de valores no concuerda con la cantidad de columnas a filtrar.
                               se pasaron %d columnas y %d valores.""".formatted(cantidadColumnas, listaValores.length));
            return false;
        }

        return true;
    }

    default String checkColumnas(String[] listaCols, String[] cols) {
        String resultado = "";
        for (String col : cols) {
            if (!Arrays.toString(listaCols).contains(col)) {
                resultado += col + ",";
            }
        }
        resultado = String.join(",", resultado.split(","));
        return resultado;
    }

    default String generarConsulta(String nombreTabla, String[] valores, String[] columnas, String metodo) {
        String consulta = "SELECT * from %s WHERE ".formatted(nombreTabla);
        String[] where = new String[valores.length];

        for (int i = 0; i < where.length; i++) {
            where[i] = "UPPER( %s ) LIKE \"%%%s%%\"".formatted(columnas[i], valores[i].toUpperCase());
        }
        consulta += String.join(" " + metodo + " ", where);
        return consulta;
    }

    default Boolean validaColumnas(String columnas, String[] listaColumnas) {

        String[] listaCols = columnas.split(",");

        if (columnas.length() == 0 || listaCols.length == 0) {
            System.out.println("""
                               No se paso ninguna columna para filtrado.
                               Las columnas deben estar separadas por coma. Formato de lista de columnas: "col1,col2,col3,...colN"
                               Columnas admitidas: %s""".formatted(Arrays.toString(listaColumnas)));
            return false;
        } else {
            String errorCheckCols = checkColumnas(listaColumnas, listaCols);
            if (!errorCheckCols.isEmpty()) {
                System.out.println("No existe la columna: \"%s\"".formatted(errorCheckCols));
                return false;
            }
        }
        return true;
    }

    default String crearSqlConsulta(String columnas, String valores, String nombreTabla, String[] listaColumnas, String metodo) throws Exception {
        if (!validaColumnas(columnas, listaColumnas)) {
            throw new Exception("Columnas incorrectas");
        }
        String[] listaCols = columnas.split(",");

        if (!validaValores(valores, listaCols.length)) {
            throw new Exception("Valores incorrectos, inconcordancia entre valores y columnas");
        }
        String[] listaValores = valores.split(",");

        return generarConsulta(nombreTabla, listaValores, listaCols, metodo);
    }
    
    default String formateo(String patron,String separador,HashMap<String,String> valores){
        String resultado = "";     
        
        int indice =0;
        int cont = 0;
        while (patron.indexOf("%s", indice)!=-1){
            cont++;
            indice = patron.indexOf("%s", indice);
            if (cont>50) {
                System.out.println("OverFlow. cont: " + cont );
                break;        
            }
        }
        
        if (cont==1) {
            for (Map.Entry<String, String> entry : valores.entrySet()) {
                resultado += patron.formatted( entry.getValue())+ separador;
            }
        }else if (cont ==2){
            for (Map.Entry<String, String> entry : valores.entrySet()) {
                resultado += patron.formatted(entry.getKey(), entry.getValue())+ separador;
            }
        }
        if (!resultado.isBlank()) {
            resultado = resultado.substring(0,resultado.length()-1);
        }
//        
//        if (cols == null ){
//            for (int i = 0; i < valores.length; i++) {
//                valores[i] = patron.formatted(valores[i]);
//            }
//            resultado = String.join(separador, valores);
//        } else{
//            String[] temp = new String[valores.length];
//            for (int i = 0; i < valores.length; i++) {
//                temp[i] = patron.formatted(cols[i],valores[i]);
//            }
//            resultado = String.join(separador, temp);
//        }

        return resultado;
    }
    
    default boolean isNull(Object c){
        
        return c == null;
    };
}
