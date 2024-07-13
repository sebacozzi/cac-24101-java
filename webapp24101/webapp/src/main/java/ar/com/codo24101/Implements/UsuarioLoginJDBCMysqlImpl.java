/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.Implements;

import ar.com.codo24101.dao.AdministradorDeConexiones;
import ar.com.codo24101.dao.UsuarioLoginDAO;
import ar.com.codo24101.dto.UsuarioLoginDTO;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Asterisco Sublimados
 */
public class UsuarioLoginJDBCMysqlImpl implements UsuarioLoginDAO<UsuarioLoginDTO> {

    public UsuarioLoginDTO getLoginUser(String user) {
        UsuarioLoginDTO ul = null;

        String sql = """
                     SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.password, ru.id_rol, r.nombre_rol FROM usuarios u\n
                    INNER JOIN roles_usuarios ru ON ru.id_usuario = u.id_usuario \n
                    INNER JOIN roles r ON r.id = ru.id_rol\n
                    WHERE u.email ='%s'""".formatted(user);

        try {
            ResultSet rs = AdministradorDeConexiones.genericoConsulta(sql);
            Long id = null;
            String nombre = null;
            String apellido = null;
            String email = null;
            String password = null;
            
            HashMap<Integer, String> roles = new HashMap<>();
            
            while (rs.next()) {
                id = rs.getLong("id_usuario");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                email = rs.getString("email");
                password = rs.getString("password");
                roles.put(rs.getInt("id_rol"), rs.getString("nombre_rol"));
            }
            
            if (id != null) {
                ul = new UsuarioLoginDTO();
                ul.setId_usuario(id);
                ul.setNombre(nombre);
                ul.setApellido(apellido);
                ul.setPassword(password);
                ul.setUsuario(email);
                ul.setRoles(roles);
            };
            
                AdministradorDeConexiones.desconectar();
            

        } catch (Exception e) {
            System.out.println("Fallo la consulta de todos los Directores. " + e);
        }

        return ul;
    }

    @Override
    public UsuarioLoginDTO changePassword(Long idUsuario, String nuevoPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
