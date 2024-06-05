package ar.com.codo24101.oop;

public class Libro extends Articulo {

    private String isbn;

    public Libro(String titulo, String autor, Float precio, String img, String isbn) {
        super(titulo, autor, precio, img);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString() + ", {Libro:{ isbn: " + isbn + "}}";
    }

}
