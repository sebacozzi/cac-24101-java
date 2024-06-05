/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.oop;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Musica extends Articulo {

    private ArrayList<String> temas;

    public Musica(String titulo, String autor, Float precio, String img, ArrayList<String> temas) {
        super(titulo, autor, precio, img);
        this.temas = temas;
    }

    public ArrayList getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<String> temas) {
        this.temas = temas;
    }

    @Override
    public String toString() {
        return super.toString() + ", {Musica:{ temas{ count: "+temas.size()+", lista: " + temas + "}}";
    }

}
