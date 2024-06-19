package ar.com.codo24101.dao;

import ar.com.codo24101.dto.DirectorDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DirectorJDBCMysqlImpl implements DAO<DirectorDTO> {

    private final String nombreTabla = "director";
    private final String[] listaColumnas = {"id_director", "nombre", "apellido", "edad", "nacionalidad"};
    private final String[] listaInsert = {"nombre", "apellido", "edad", "nacionalidad"};

    @Override
    public void create(DirectorDTO directorDto) {
        String sql = "INSERT INTO %s(%s) VALUES(%s) ";
        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                formateo("\"%s\"", ", ", directorDTOAArray(directorDto),null));

        try {
            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nuevo director. " + e);
        }

    }

    @Override
    public void update(DirectorDTO directorDto) {
        String sql = "UPDATE %s SET nombre = \"%s\", apellido = \"%s\", edad = \"%d\" , nacionalidad = \"%s\" WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla, directorDto.getNombre(),
                formateo("%s = \"%s\"", ", ", directorDTOAArray(directorDto),listaInsert),
                directorDto.getId_director().toString());

        try {

            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al actualizar el director. " + e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM %s WHERE id_director = %d".formatted(nombreTabla,id);
        
        try {
            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al interntar eliminar el director" + e);
        }
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
            d.setId_director(r.getLong("id_movie"));
            d.setNombre(r.getString("nombre"));
            d.setApellido(r.getString("descripcion"));
            d.setNacionalidada(r.getString("genero"));
            d.setEdad(r.getLong("anio"));

            return d;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }
    
    private String directorDTOAArray(DirectorDTO d){
        String resultado = "";
        
        if(d.getNombre() != null){
            resultado += d.getNombre();
        }
        if(d.getNombre() != null){
            resultado += d.getNombre();
        }
        if(d.getNombre() != null){
            resultado += d.getNombre();
        }
        if(d.getNombre() != null){
            resultado += d.getNombre();
        }
        resultado[0]= d.getNombre();
        resultado[1] = d.getApellido();
        resultado[2] = d.getEdad().toString();
        resultado[3] = d.getNacionalidad();
        
        return resultado;
    }

}
