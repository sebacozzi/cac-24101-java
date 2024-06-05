package ar.com.codo24101.oop;

import java.util.ArrayList;

public class Buscador {

    private String claveDeBusqueda;
    private ArrayList<Articulo> resultados;

    public Buscador() {
        resultados = new ArrayList<>();
    }

    public int getCantidad() {
        return resultados.size();
    }

    public void setClave(String clave) {
        if (clave == null) {
            claveDeBusqueda = "";
        } else {
            claveDeBusqueda = clave;
        }
    }

    public void buscar() {
        for (int i = 0; i < 16; i++) {
            String id = String.valueOf(i + 1);
            Libro l = new Libro("titulo "+ id, "autor " + id,154f, "Imagen "+ id,"isbn "+id);
        }
    }
}
