package ar.com.codo24101;

import java.time.LocalDate;

public class Vehiculo {

    private String marca;
    private String modelo;
    private String chasis;
    private Integer anio;
    private Integer velocidad;
    private Integer vMax;
    private Boolean encendido;
    private LocalDate fechaCreacion;

    public Vehiculo(String marca, String modelo, String chasis, Integer anio, Integer vMax) {
        this.marca = marca;
        this.modelo = modelo;
        this.chasis = chasis;
        this.anio = anio;
        this.vMax = vMax;
        velocidad = 0;
        encendido = false;
        fechaCreacion = LocalDate.now();
    }
    
    public void encender(){
        if (!encendido) {
            encendido = true;
        } else{
            System.out.println("El vehiculo ya está encendido");
        }
    }

    public void apagar(){
        if (encendido) {
            encendido = false;
        } else {
            System.out.println("El vehiculo ya está apagado");
        }
    }

    public void acelerar (){
        if (encendido){
            if (velocidad < vMax) {
                velocidad ++;
            }else{
                System.out.println("Alto Toreto!! Vas a maxima velocidad");
            }
        } else{
            System.out.println("El vehiculo está apagado");
        }
    }

    public void frenar (){
        if (encendido){
            if (velocidad > 0) {
                velocidad --;
            }else{
                System.out.println("El vehiculo está frenado");
            }
        } else{
            System.out.println("El vehiculo está apagado");
        }
    }

    public Integer obtenerVelocidad(){
        return velocidad;
    }

    public LocalDate obtenerFechaCreacion(){
        return fechaCreacion;
    }

    public void mostrarDatos() {
        System.out.println("{Vehiculo : {marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", chasis=" + chasis
                + ", velocidad=" + velocidad + ", vmax=" + vMax + ", encendido=" + encendido + ", fechaCreacion="
                + formatearFecha(fechaCreacion.toString()) + "}}");
    }

    public void detener(){
        if (encendido){
            System.out.println("La velocidad del vehiculo es: %d km/h".formatted(velocidad ));
            for (int i = velocidad; i > 0; i--) {
                velocidad --;
                System.out.println(velocidad + "km/h");
            }
            System.out.println("El vehiculo se a detenido");
        }
    }

    private String formatearFecha(String fecha){
        String[] t= fecha.split("-");
        String[] f = new String[t.length];
        Integer j = f.length-1;
        for (int i = 0; i < t.length; i++) {
            f[j]=t[i];
            j--;
        }
        return String.join("/",f);
    }
}
