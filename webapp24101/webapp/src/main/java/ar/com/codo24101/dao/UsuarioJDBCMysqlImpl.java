/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.dao;
import ar.com.codo24101.dto.UsuarioDTO;
import java.sql.ResultSet;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UsuarioJDBCMysqlImpl implements DAO<UsuarioDTO>{
    private final String nombreTabla = "usuarios";
    private final String[] listaColumnas = {"id_usuario","nombre","apellido","email","fecha_nac","pais"};
    private final String[] listaInsert = {"nombre","apellido","email","fecha_nac","pais"};
    @Override
    public void create(UsuarioDTO usuarioDto) {
        String sql = "INSERT INTO %s(%s) VALUES(%s) ";
        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                formateo("\"%s\"", ", ", directorDTOAArray(usuarioDto),null));

        try {
            AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nuevo director. " + e);
        }

    }

    @Override
    public void update(UsuarioDTO usuarioDto) {
        String sql = "UPDATE %s SET nombre = \"%s\", apellido = \"%s\", edad = \"%d\" , nacionalidad = \"%s\" WHERE id_movie = \"%d\"";

        sql = sql.formatted(nombreTabla, usuarioDto.getNombre(),
                formateo("%s = \"%s\"", ", ", directorDTOAArray(usuarioDto),listaInsert),
                usuarioDto.getId_usuario().toString());

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
    public ArrayList<UsuarioDTO> getLista() {
        ArrayList<UsuarioDTO> u = new ArrayList<>();
       
       String sql = "SELECT * FROM %s".formatted(nombreTabla);
       
        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);
            
            while (rs.next()) {
                u.add(resultadoADirectorDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todos los Directores. " + e);
        }
       
       return u;
    }

    @Override
    public ArrayList<UsuarioDTO> getByVal(String columnas, String valores, String metodo) {
        ArrayList<UsuarioDTO> u = new ArrayList<>();
        try {
            String sql = crearSqlConsulta(columnas, valores, nombreTabla, listaColumnas, metodo);

            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            while (rs.next()) {
                u.add(resultadoADirectorDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Se produjo una falla al obtener la lista de filtrada de Directores. " + e);
            return null;
        }
        return u;
    }
    
    private UsuarioDTO resultadoADirectorDTO(ResultSet r) {
        try {
            UsuarioDTO d = new UsuarioDTO();
            d.setId_usuario(r.getLong(listaColumnas[0]));
            d.setNombre(r.getString(listaColumnas[1]));
            d.setApellido(r.getString(listaColumnas[2]));
            d.setEmail(r.getString(listaColumnas[3]));
            d.setFecha_nac(r.getDate(listaColumnas[4]).toLocalDate());
            d.setPais(r.getString(listaColumnas[5]));
            
            return d;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }
    
    private String[] directorDTOAArray(UsuarioDTO u){
        String[] resultado = new String[5];
        
        resultado[0]= u.getNombre();
        resultado[1] = u.getApellido();
        resultado[2] = u.getEmail();
        resultado[3] = u.getFecha_nac().toString();
        resultado[4] = u.getPais();
        return resultado;
    }
}
