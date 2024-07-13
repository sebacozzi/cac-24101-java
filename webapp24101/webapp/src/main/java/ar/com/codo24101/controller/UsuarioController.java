/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.controller;

import ar.com.codo24101.Interfaces.GenericResults;
import ar.com.codo24101.Interfaces.Validaciones;
import ar.com.codo24101.dto.UsuarioCRUDDTO;
import ar.com.codo24101.dto.UsuarioDTO;
import ar.com.codo24101.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
@WebServlet("/usuarios/*")
public class UsuarioController extends HttpServlet implements GenericResults, Validaciones {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if (puedePost(resp.getHeader("Access-Control-Allow-Methods"))) {
        
            ObjectMapper om = new ObjectMapper();

             UsuarioCRUDDTO uDto = om.readValue(req.getReader(), UsuarioCRUDDTO.class);
            
            UsuarioDTO u = uDto.getObjeto();
            
            if (u != null) {
                UsuarioService servicio = new UsuarioService();
                /// no pude manejar los privilegios para los otros verbos y decidi realizar un objeto que contenga la acción y el objeto de muestra
                
                switch (uDto.getMetodo().toUpperCase()) {
                    case "POST":
                        if (!servicio.crear(u)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo crear el usuario %s"}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Usuario %s creado con exito."}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_CREATED);
                        }
                        break;
                    case "DELETE":
                        if (!servicio.eliminarUsuario(u.getId_usuario())) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo borrar el usuario %s."}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Usuario %s eliminado con exito."}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_OK);
                        }
                        break;
                    case "PUT":
                        if (servicio.actualizarUsuario(u)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo actualizar el usuario %s."}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);

                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Usuario %s actualizado con exito."}
                                             """.formatted(u.getNombre() + " " + u.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_OK);
                        }
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI().split("/")[req.getRequestURI().split("/").length - 1];
        String resultado;

        if (uri.equals("webapp")) {
            resultado = nullURL();
        } else {
            UsuarioService servicio = new UsuarioService();
            ObjectMapper om = new ObjectMapper();
            boolean fallo = false;
            String mensajeFallo = "";
            ArrayList<UsuarioDTO> lista = null;
            switch (uri) {
                case "all":
                    lista = servicio.obtenerUsuarios();
                    break;
                case "filtro":
                    switch (validaParametros(req.getParameterNames())) {
                        case -1:
                            fallo = true;
                            mensajeFallo = "[Parametros Invialidos]";
                            break;
                        case 1:
                            fallo = true;
                            mensajeFallo = "[La cantidad de parametros es incorrecto]";
                            break;
                        case 2:
                            lista = servicio.obtenerPorColumnaValor(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0]);
                            break;
                        case 3:
                            lista = servicio.obtenerPorColumnaValor(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0], req.getParameterValues("caso")[0]);
                            break;
                    }
                    break;
                default:
                    if (esNumerico(uri)) {
                        lista = new ArrayList<>();
                        lista.add(servicio.obtenerUsuarioPorID(Long.valueOf(uri)));
                    }
                    ;
            }
            if (fallo) {
                resultado = formatedResult(0, mensajeFallo);
            } else {
                System.out.println(lista);
                resultado = formatedResult(lista == null ? 0 : lista.get(0) == null ? 0 : lista.size(), om.writeValueAsString(lista));
            }
        }
        resp.getWriter().println(resultado);
    }
}
