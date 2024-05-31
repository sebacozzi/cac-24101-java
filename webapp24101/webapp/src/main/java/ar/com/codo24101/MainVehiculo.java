package ar.com.codo24101;

public class MainVehiculo {
    public static void main(String[] args) {

        Vehiculo clio = new Vehiculo("renault", "Clio", "123456789", 2024, 150);

       // clio.encender();

        clio.acelerar();
        clio.acelerar();
        clio.acelerar();
        clio.acelerar();
        clio.acelerar();
        
        clio.acelerar();

        clio.detener();
       
        clio.mostrarDatos();

    }
}
