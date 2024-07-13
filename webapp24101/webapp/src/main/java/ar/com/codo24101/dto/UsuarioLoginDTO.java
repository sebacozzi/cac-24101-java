/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.dto;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class UsuarioLoginDTO {
    private Long id_usuario;
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
    private String url_imagen;
    private HashMap<Integer,String> roles;
    

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(Long id_usuario, String usuario, String nombre, String apellido, String password, String url_imagen,HashMap<Integer, String> roles) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.url_imagen = url_imagen;
        this.roles = roles;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public HashMap<Integer,String> getRoles() {
        return roles;
    }

    public void setRoles(HashMap<Integer,String> roles) {
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "UsuarioLoginDTO{" + "id_usuario=" + id_usuario + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", password=" + password + ", roles=" + roles + '}';
    }

    public String rolesAString() {
       String res="";
       
        for (Integer val : roles.keySet()) {
            res += val+"-";
        }
        
        return res.substring(0, res.length()-1);
    }
    
}
