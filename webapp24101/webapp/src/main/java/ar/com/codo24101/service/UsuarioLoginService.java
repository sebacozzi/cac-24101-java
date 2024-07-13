package ar.com.codo24101.service;

import ar.com.codo24101.Implements.UsuarioLoginJDBCMysqlImpl;
import ar.com.codo24101.dao.UsuarioLoginDAO;
import ar.com.codo24101.dto.TokenLogin;
import ar.com.codo24101.dto.UsuarioLoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Calendar;
import java.util.Date;

public class UsuarioLoginService {

    private UsuarioLoginDAO<UsuarioLoginDTO> ulDao;

    public UsuarioLoginService() {
        ulDao = new UsuarioLoginJDBCMysqlImpl();
    }

    public UsuarioLoginDTO verificaUsuario(UsuarioLoginDTO usuario) {
        UsuarioLoginDTO ul = ulDao.getLoginUser(usuario.getUsuario());
        if (ul == null) {
            return null;
        }

        boolean validaPass = usuario.getPassword().equals(ul.getPassword());

        if (validaPass) {
            return ul;
        } else {
            return null;
        }
    }

    ;

    public String crearJWT(UsuarioLoginDTO usuarioLogin) throws JsonProcessingException, Exception {
        if (usuarioLogin == null) {
            return "Usuario, contraseña o ambos incorrectos.";
        }
        TokenLogin tl = new TokenLogin(usuarioLogin);

        ObjectMapper om = new ObjectMapper();
        String ret = EncriptarDesencriptar.encrypt(om.writeValueAsString(tl));
        
        return ret;
    }
    
    public static String checkToken(String token){
            String resultado = "";
            
            if (token.isBlank()){
                return "Fallo: token vacio";
            }
        try {
            
            String decr = EncriptarDesencriptar.decrypt(token);
            ObjectMapper om = new ObjectMapper();
            TokenLogin tl = om.readValue(decr, TokenLogin.class);
            
            if( !estaATiempo(tl.getFechaCreacion(),tl.getTiempoVence())){
                return "Fallo: tiempo vencido";
            } 
            
            if (tl.getUl().getRoles().containsKey(1)){
                resultado = "GET";
                if(tl.getUl().getRoles().containsKey(2)){
                    resultado = "POST, GET, OPTIONS, DELETE, PUT";
                }
            } 
            
            return resultado;
        } catch (Exception ex) {
            System.out.println("------------------------- Error al desencriptar -------------------------\n"+ex);
            
            return "Fallo: error de token";
        }
    
    }
    private static boolean estaATiempo(Date fechaCreacion,int tiempoVence){
        
        Calendar calen = Calendar.getInstance();
        calen.setTime(fechaCreacion);
        calen.add(Calendar.MINUTE, tiempoVence);
        
        Date fechaCalc = calen.getTime();
        
        return fechaCalc.after(new Date());
        
    }
}
