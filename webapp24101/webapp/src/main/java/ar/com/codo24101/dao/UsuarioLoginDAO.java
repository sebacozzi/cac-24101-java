package ar.com.codo24101.dao;

public interface UsuarioLoginDAO<T> {

    default String KEY(){
        return "clave_de_sifrado.";
    };
    
    public T getLoginUser(String nombreUsuario);
    public T changePassword(Long idUsuario,String nuevoPassword);
}
