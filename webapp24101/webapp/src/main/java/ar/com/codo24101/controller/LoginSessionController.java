/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.controller;


import ar.com.codo24101.dto.UsuarioLoginDTO;
import ar.com.codo24101.service.UsuarioLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
@WebServlet("/private/login/")
public class LoginSessionController extends HttpServlet {

    //private static final long serialVersionUID = 1L;
  

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ejecuto LoginSession");
        
        /// java bcrypt jbcrypt
        
        ObjectMapper om = new ObjectMapper();
        UsuarioLoginService uls = new UsuarioLoginService();
        
        UsuarioLoginDTO usuarioLogin = om.readValue(req.getReader(), UsuarioLoginDTO.class);
        // recibo email y password
        //System.out.println(usuarioLogin);
        
        System.out.println("Usuario para login: "+ usuarioLogin);
        usuarioLogin = uls.verificaUsuario(usuarioLogin);
        System.out.println("Usuario verificado: "+ usuarioLogin);
        
        if (usuarioLogin == null){
            /// 
            resp.setStatus(401);
            resp.getWriter().print("{\"result\": \"" +"usuario inexistente"+ "\"}");
            resp.getWriter().flush();            
        } else{
            try {
                resp.setStatus(201);
                
                String tok =uls.crearJWT(usuarioLogin);
                
                resp.getWriter().print("""
                                       {"nombre":"%s",
                                       "apellido":"%s",
                                       "roles":"%s",
                                       "vence":"%s",
                                       "token": "%s"}""".formatted(usuarioLogin.getNombre(),usuarioLogin.getApellido() ,usuarioLogin.rolesAString(),fechaHora(),tok));            
                resp.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(LoginSessionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private String fechaHora(){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MINUTE, 30);
        return ca.getTime().toString();        
    }
}