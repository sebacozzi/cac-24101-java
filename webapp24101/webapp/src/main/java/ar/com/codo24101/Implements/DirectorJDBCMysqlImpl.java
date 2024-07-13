package ar.com.codo24101.Implements;

import ar.com.codo24101.dao.AdministradorDeConexiones;
import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.dto.DirectorDTO;
import java.sql.ResultSet;
import java.util.ArrayList;



public class DirectorJDBCMysqlImpl implements DAO<DirectorDTO> {

    private final String nombreTabla = "directores";
    private final String[] listaColumnas = {"id_director", "nombre", "apellido", "edad", "nacionalidad","url_imagen"};
    private final String[] listaInsert = {"nombre", "apellido", "edad", "nacionalidad","url_imagen"};

    @Override
    public boolean create(DirectorDTO directorDto) {
        String sql = "INSERT INTO %s (%s) VALUES(%s) ";
        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                directorDTOAinsertString(directorDto));
boolean ret = false;
        try {
            ret = AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nuevo director. " + e);
        }
return ret;
    }

    @Override
    public boolean update(DirectorDTO directorDto) {
        String sql = "UPDATE %s SET %s WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla, directorDto.getNombre(),
                directorDTOAStringUpdate(directorDto),
                directorDto.getId_director().toString());
boolean ret = false;
        try {

            ret = AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al actualizar el director. " + e);
        }
        return ret;
    }

    @Override
    public DirectorDTO getByName(String nombre) {
        String sql = "SELECT * FROM %s WHERE nombre = \"%s\"";
        DirectorDTO d = null;
        try {
            sql = sql.formatted(nombreTabla, nombre);
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            if (rs.next()) {
                d = resultadoADirectorDTO(rs);
            }

        } catch (Exception e) {
            System.out.println("Error al Buscar el director. " + e);
        } finally {
            AdministradorDeConexiones.desconectar();
            return d;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM %s WHERE id_director = %d".formatted(nombreTabla,id);
        boolean ret = false;
        try {
            ret=AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al interntar eliminar el director" + e);
        }
        return ret;
    }

    @Override
    public ArrayList<DirectorDTO> getLista() {
       ArrayList<DirectorDTO> d = new ArrayList<>();
       
       String sql = "SELECT * FROM %s".formatted(nombreTabla);
       
        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);
            
            while (rs.next()) {
                d.add(resultadoADirectorDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todos los Directores. " + e);
        }
       
       return d;
    }

    @Override
    public ArrayList<DirectorDTO> getByVal(String columnas, String valores, String metodo) {
        ArrayList<DirectorDTO> d = new ArrayList<>();
        try {
            String sql = crearSqlConsulta(columnas, valores, nombreTabla, listaColumnas, metodo);

            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                d.add(resultadoADirectorDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Se produjo una falla al obtener la lista de filtrada de Directores. " + e);
            return null;
        }
        return d;
    }

    private DirectorDTO resultadoADirectorDTO(ResultSet r) {
        try {
            DirectorDTO d = new DirectorDTO();
            d.setId_director(r.getLong("id_director"));
            d.setNombre(r.getString("nombre"));
            d.setApellido(r.getString("apellido"));
            d.setNacionalidad(r.getString("nacionalidad"));
            d.setEdad(r.getLong("edad"));

            return d;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }
    
    public String directorDTOAinsertString(DirectorDTO d){
        String resultado = "";
        
        if (d.getNombre()!=null){
        resultado = resultado +"\"" + d.getNombre()+"\",";    
        }
        if (d.getApellido() !=null) {
        resultado = resultado +"\"" + d.getApellido()+"\",";
        }
        if(d.getEdad()!= null){
            resultado = resultado +"\"" + d.getEdad().toString()+"\",";
        }
        if (d.getNacionalidad() != null){
            resultado = resultado +"\"" +  d.getNacionalidad()+"\",";
        }
        if (d.getUrl_imagen()!= null){
            resultado = resultado +"\"" +  d.getUrl_imagen()+"\",";
        }
        
        return resultado.substring(0, resultado.length()-1);
    }
    
    private String directorDTOAStringUpdate(DirectorDTO d){
        String resultado = "";
        
        if (d.getNombre()!=null){
        resultado = resultado +"nombre = \"" + d.getNombre()+"\",";    
        }
        if (d.getApellido() !=null) {
        resultado = resultado +"apellido = \"" + d.getApellido()+"\",";
        }
        if(d.getEdad()!= null){
            resultado = resultado +"edad = \"" + d.getEdad().toString()+"\",";
        }
        if (d.getNacionalidad() != null){
            resultado = resultado +"nacionalidad =\"" +  d.getNacionalidad()+"\",";
        }
        if (d.getUrl_imagen()!= null){
            resultado = resultado +"url_imagen = \"" +  d.getUrl_imagen()+"\",";
        }
        
        return resultado.substring(0, resultado.length()-1);
    }
}
