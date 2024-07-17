package ar.com.codo24101.Implements;

import ar.com.codo24101.dao.AdministradorDeConexiones;
import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.dto.UsuarioDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


public class UsuarioJDBCMysqlImpl implements DAO<UsuarioDTO>{
    private final String nombreTabla = "usuarios";
    private final String[] listaColumnas = {"id_usuario","nombre","apellido","email","fecha_nac","pais","password","url_imagen"};
    private final String[] listaInsert = {"nombre","apellido","email","fecha_nac","pais","password","url_imagen"};

    @Override
    public boolean create(UsuarioDTO usuarioDto) {
        String sql = "INSERT INTO %s(%s) VALUES(%s) ";
        sql = sql.formatted(nombreTabla,
                String.join(", ", listaInsert),
                UsuarioDTOAStringInsert(usuarioDto));
        boolean ret=false;
        try {
            ret= AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al insertar nuevo director. " + e);
        }
        return ret;
    }

    @Override
    public UsuarioDTO getByName(String nombre) {
        String sql = "SELECT * FROM %s WHERE nombre = '%s' and apellido = '%s' ";
        UsuarioDTO u = null;
        try {
            sql = sql.formatted(nombreTabla, nombre.split(",")[0],nombre.split(",")[1]);
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);

            if (rs.next()) {
                u = resultadoAUsuarioDTO(rs);
            }

        } catch (Exception e) {
            System.out.println("Error al Buscar el usuario. " + e);
        } finally {
            AdministradorDeConexiones.desconectar();
            return u;
        }
    }
    
    @Override
    public boolean update(UsuarioDTO usuarioDto) {
        String sql = "UPDATE %s SET %s WHERE id_usuario = \"%s\"";

        sql = sql.formatted(nombreTabla, usuarioDto.getNombre(),
                UsuarioDTOAStringUpdate(usuarioDto),
                usuarioDto.getId_usuario().toString());
        boolean ret= false;
        try {
            ret=AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario. " + e);
        }
        return ret;
    }

    @Override
    public boolean delete(Long id) {
       String sql = "DELETE FROM %s WHERE id_usuario = %d".formatted(nombreTabla,id);
        boolean ret = false;
        try {
            ret= AdministradorDeConexiones.genericoCrearActualizarBorrar(sql);
        } catch (Exception e) {
            System.out.println("Error al interntar eliminar el usuario. " + e);
        }
        return ret;
    }

    @Override
    public ArrayList<UsuarioDTO> getLista() {
        ArrayList<UsuarioDTO> u = new ArrayList<>();
       
       String sql = "SELECT * FROM %s".formatted(nombreTabla);
       
        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);
            
            while (rs.next()) {
                u.add(resultadoAUsuarioDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Fallo la consulta de todos los usuarios. " + e);
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
                u.add(resultadoAUsuarioDTO(rs));
            }
            AdministradorDeConexiones.desconectar();
        } catch (Exception e) {
            System.out.println("Se produjo una falla al obtener la lista de filtrada de usuarios. " + e);
            return null;
        }
        return u;
    }
    
    private UsuarioDTO resultadoAUsuarioDTO(ResultSet r) {
        try {
            UsuarioDTO d = new UsuarioDTO();
            
            d.setId_usuario(r.getLong(listaColumnas[0]));
            d.setNombre(r.getString(listaColumnas[1]));
            d.setApellido(r.getString(listaColumnas[2]));
            d.setEmail(r.getString(listaColumnas[3]));
            d.setFecha_nac(r.getDate(listaColumnas[4]).toLocalDate());
            d.setPais(r.getString(listaColumnas[5]));
            d.setPassword(r.getString(listaColumnas[6]));
            d.setUrl_imagen(r.getString(listaColumnas[7]));
         
            return d;
        } catch (Exception e) {
            System.out.println("Se produjo al intentar obtener los datos de la consulta. " + e);
            return null;
        }
    }
    
    private String UsuarioDTOAStringInsert(UsuarioDTO u){
        String resultado = "";
        
        if(u.getNombre()!=null){
        resultado= resultado + "\""+ u.getNombre()+"\",";
        }
        if(u.getApellido()!=null){
        resultado= resultado + "\""+  u.getApellido()+"\",";
        }
        if(u.getEmail()!=null){
        resultado= resultado + "\""+   u.getEmail()+"\",";
        }
        if(u.getFecha_nac()!= null){
        resultado= resultado + "\""+ u.getFecha_nac().toString()+"\",";
        }
        if(u.getPais() !=null){
        resultado= resultado + "\""+ u.getPais()+"\",";
        }
        if(u.getPassword() != null){
        resultado= resultado + "\""+  u.getPassword()+"\",";
        }
        if(u.getUrl_imagen()!=null){
        resultado= resultado + "\""+  u.getUrl_imagen()+"\",";
        }
        
        return resultado.substring(0,resultado.length()-1);
    }
    private String UsuarioDTOAStringUpdate(UsuarioDTO u){
        String resultado = "";
        
        if(u.getNombre()!=null){
        resultado= resultado + "nombre = \""+ u.getNombre()+"\",";
        }
        if(u.getApellido()!=null){
        resultado= resultado + "apellido =\""+  u.getApellido()+"\",";
        }
        if(u.getEmail()!=null){
        resultado= resultado + "email =\""+   u.getEmail()+"\",";
        }
        if(u.getFecha_nac()!= null){
        resultado= resultado + "fecha_nac\""+ u.getFecha_nac().toString()+"\",";
        }
        if(u.getPais() !=null){
        resultado= resultado + "pais = \""+ u.getPais()+"\",";
        }
        if(u.getPassword() != null){
        resultado= resultado + "password = \""+  u.getPassword()+"\",";
        }
        if(u.getUrl_imagen()!=null){
        resultado= resultado + "url_imagen = \""+  u.getUrl_imagen()+"\",";
        }
        
        return resultado.substring(0,resultado.length()-1);
    }
}
