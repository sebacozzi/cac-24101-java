/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.service;

import ar.com.codo24101.Implements.UsuarioJDBCMysqlImpl;
import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.dto.UsuarioDTO;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UsuarioService {
    private DAO<UsuarioDTO> daoU;

    public UsuarioService() {
        daoU = new UsuarioJDBCMysqlImpl();
    }

    public ArrayList<UsuarioDTO> obtenerUsuarios() {
        return daoU.getLista();
    }

    public ArrayList<UsuarioDTO> obtenerPorColumnaValor(String columnas, String valores) {
        return daoU.getByVal(columnas, valores);
    }

    public ArrayList<UsuarioDTO> obtenerPorColumnaValor(String columnas, String valores, String metodo) {
        return daoU.getByVal(columnas, valores, metodo);
    }

    public UsuarioDTO obtenerUsuarioPorID(Long idUsuario) {
        return daoU.getByID("id_usuario", idUsuario);
    }

    public boolean crear(UsuarioDTO u) {
        daoU.create(u);
        return daoU.getByVal("nombre,apellido", u.getNombre() + ","+u.getApellido())!=null;
    }

    public boolean eliminarUsuario(Long id_usuario) {
        daoU.delete(id_usuario);
        return daoU.getByID("id_usuario", id_usuario)==null;
    }

    public boolean actualizarUsuario(UsuarioDTO u) {
        daoU.update(u);
        return true;
    }
    
    
    
    
}
