/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.com.codo24101.dto;

/**
 *
 * @author Usuario
 */
public class DirectorDTO {

    private Long id_director;
    private String nombre;
    private String apellido;
    private Long edad;
    private String nacionalidad;
    private String url_imagen;

    
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "Director{" + "id_director=" + id_director + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", nacionalidad=" + nacionalidad + '}';
    }
    
  
    /**
     *
     * @return Array [0] valores , [1] campos
     */

    public String[] generarValues(){
        String valores = "";
        String campos ="";
        if(!nombre.isBlank()){
            valores += nombre+ ",";
            campos += "nombre,";
        }
        if(!apellido.isBlank()){
            valores +=apellido + ",";
            campos += "apellido,";
        }
        if(edad !=0){
            valores +=edad.toString() + ",";
            campos += "edad,";
        }
        if(!nacionalidad.isBlank()){
            valores += nacionalidad +",";
            campos += "nacionalidad,";
        }
        String[] res={valores.substring(0, (valores.length()-1)), campos.substring(0, (campos.length()-1))};
        return res;
    }
}
