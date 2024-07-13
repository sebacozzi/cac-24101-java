/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.filters;

import ar.com.codo24101.Interfaces.Validaciones;
import ar.com.codo24101.service.UsuarioLoginService;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 *
 * @author Asterisco Sublimados
 */
@WebFilter(urlPatterns = {"/*"})
public class CorsFilter implements Filter,Validaciones{

    private final List<String> origins = List.of("http://localhost", "http://127.0.0.1", "http://localhost:5501", "http://127.0.0.1:5501", "http://localhost:5500", "http://127.0.0.1:5500", "http://127.0.0.1:5501/practica", "http://localhost:5501/practica");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        String origin = ((HttpServletRequest) request).getHeader("origin");
        String metodo = ((HttpServletRequest) request).getMethod();
        
        String accessControl = ((HttpServletRequest) request).getHeader("access-control-request-headers");
        
        
        
        if (metodo != null && metodo.equals("OPTIONS")) {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "OPTIONS");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
            chain.doFilter(request, response);
            return;
        }
        if (esUriLogin(((HttpServletRequest) request).getRequestURI())) {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
            
            
        } else if (metodo.equalsIgnoreCase("get")) {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
            
            
        } else {
            String auth = ((HttpServletRequest) request).getHeader("Authorization");
           
             if (!auth.isBlank()) {
                String access = UsuarioLoginService.checkToken(auth);
                if (!access.isBlank()) {
                    if(access.contains("Fallo")){
                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, access);
                        
                        
                    }else{
                    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);
                    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", access);
                    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
                   
                    
                    }
                }
            } else if ((origin != null && origins.contains(origin)) ) {
                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);
                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
                ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
                
            }
        }
chain.doFilter(request, response);
    }

    private void vistaDatos(HttpServletRequest request) {
        System.out.println("");
        System.out.println("*".repeat(15) + "Vista Datos en CorsFilter" + "*".repeat(15));

        Enumeration<String> en = request.getHeaderNames();
        System.out.println("-".repeat(50));
        System.out.println("Headers Names:");
        System.out.println("-".repeat(50));

        while (en.hasMoreElements()) {
            String val = en.nextElement();
            System.out.println("Nombre: " + val + ":".repeat(5) + " Valor: " + request.getHeader(val));
        }

        en = request.getParameterNames();
        System.out.println("-".repeat(50));
        System.out.println("Parameters Names:");
        System.out.println("-".repeat(50));
        while (en.hasMoreElements()) {
            String val = en.nextElement();
            System.out.println("Nombre Atributo: " + val);
        }
        System.out.println("-".repeat(50));
        System.out.println("Metodo::  " + request.getMethod());
        System.out.println("");
        System.out.println("*".repeat(15) + "Vista Datos en CorsFilter" + "*".repeat(15));
    }
}
