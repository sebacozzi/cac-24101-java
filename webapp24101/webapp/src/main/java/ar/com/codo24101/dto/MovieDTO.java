package ar.com.codo24101.dto;

public class MovieDTO {


    private Long id_movie;
    private String nombre;
    private String descripcion;
    private String genero;
    private Float calificacion;
    private Long anio;
    private Long estrellas;
    private String url_imagen;
    private Long director; //Director

    public MovieDTO() {
    }

    public MovieDTO(Long id_movie, String nombre, String descripcion, String genero, Float calificacion, Long anio, Long estrellas,String url_imagen, Long director) {
        this.id_movie = id_movie;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.anio = anio;
        this.estrellas = estrellas;
        this.url_imagen = url_imagen;
        this.director = director;
    }
    
    
    
    public Long getId_movie() {
        return id_movie;
    }

    public void setId_movie(Long id_movie) {
        this.id_movie = id_movie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public Long getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Long estrellas) {
        this.estrellas = estrellas;
    }

    public Long getDirector() {
        return director;
    }

    public void setDirector(Long director) {
        this.director = director;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "id_movie=" + id_movie + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + ", calificacion=" + calificacion + ", anio=" + anio + ", estrellas=" + estrellas + ", director=" + director + '}';
    }

}
