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
            int caso = (int) (Math.random() * 5);
            Float precio = (float) (Math.random() * 10000f +5000);
            switch (caso) {
                case 0:
                    resultados.add(new Libro("titulo " + id, "autor " + id, precio, "Imagen " + id, "isbn " + id));
                    break;
                case 1:
                    resultados.add(new Pelicula("titulo " + id, "autor " + id, precio, "Imagen " + id, "genero " + id));
                    break;
                case 2:
                    ArrayList<String> temas = new ArrayList<>();
                    int countTemas = ((int) (Math.random() * 7 + 7));

                    for (int j = 1; j < countTemas; j++) {
                        temas.add("temas " + j);
                    }

                    resultados.add(new Musica("Musica " + id, "Autor" + id, precio, "Caratula " + id, temas));
                    break;
                case 3:
                    break;
                case 4:
            }
            System.out.print(caso + "-");
        }
        System.out.println("");
    }

    public void mostrarResultados() {
        System.out.println("Hemos encontrado %d resultados para %s".formatted(getCantidad(), claveDeBusqueda));
        for (Articulo a : resultados) {
            System.out.println(a.toString());
        }
    }
}
