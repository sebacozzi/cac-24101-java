
package ar.com.codo24101.dto;

import java.util.Date;

public class TokenLogin {
     private UsuarioLoginDTO ul;
     private Integer tiempoVence;
     private Date fechaCreacion;

    public TokenLogin(UsuarioLoginDTO ul, Integer tiempoVence, Date fechaCreacion) {
        this.ul = ul;
        this.tiempoVence = tiempoVence;
        this.fechaCreacion = fechaCreacion;
    }

    public TokenLogin() {
    }
    
    public TokenLogin(UsuarioLoginDTO ul) {
        this.ul = ul;
        this.tiempoVence = 30;
        this.fechaCreacion = new Date();    
    }

    public UsuarioLoginDTO getUl() {
        return ul;
    }

    public void setUl(UsuarioLoginDTO ul) {
        this.ul = ul;
    }

    public Integer getTiempoVence() {
        return tiempoVence;
    }

    public void setTiempoVence(Integer tiempoVence) {
        this.tiempoVence = tiempoVence;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    @Override
    public String toString() {
        return "TokenLogin{" + "ul=" + ul + ", tiempoVence=" + tiempoVence + ", fechaCreacion=" + fechaCreacion + '}';
    }
     
     
}
