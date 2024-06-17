/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.domain;

/**
 *
 * @author Usuario
 */
public class Director {
    private Long id_director;
    private String nombre;
    private String apellido;
    private Long edad;
    private String nacionalidada;

    public Director(Long id_director, String nombre, String apellido, Long edad, String nacionalidada) {
        this.id_director = id_director;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidada = nacionalidada;
    }

    public Director() {
    }

    public Long getId_director() {
        return id_director;
    }

    public void setId_director(Long id_director) {
        this.id_director = id_director;
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

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public String getNacionalidada() {
        return nacionalidada;
    }

    public void setNacionalidada(String nacionalidada) {
        this.nacionalidada = nacionalidada;
    }

    
    @Override
    public String toString() {
        return "Director{" + "id_director=" + id_director + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", nacionalidada=" + nacionalidada + '}';
    }
    
}
