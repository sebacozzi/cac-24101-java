package ar.com.codo24101.oop;

public class MainBuscador {
    public static void main(String[] args){
        
        String claveEnviadaPorElUsuarioEnElForm = "harry potter";
        Buscador b = new Buscador();
        
        b.setClave(claveEnviadaPorElUsuarioEnElForm);
        
        System.out.println("Cantidad: "+ b.getCantidad());
        
        b.buscar();
        
        b.mostrarResultados();
    }
}
