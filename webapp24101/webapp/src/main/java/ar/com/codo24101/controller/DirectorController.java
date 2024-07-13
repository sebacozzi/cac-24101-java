package ar.com.codo24101.controller;

import ar.com.codo24101.Interfaces.GenericResults;
import ar.com.codo24101.Interfaces.Validaciones;
import ar.com.codo24101.dto.DirectorCRUDDTO;
import ar.com.codo24101.dto.DirectorDTO;
import ar.com.codo24101.service.DirectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/directores/*")
public class DirectorController extends HttpServlet implements GenericResults,Validaciones {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if (puedePost(resp.getHeader("Access-Control-Allow-Methods"))) {
        
            ObjectMapper om = new ObjectMapper();

             DirectorCRUDDTO pDto = om.readValue(req.getReader(), DirectorCRUDDTO.class);
            
            DirectorDTO d = pDto.getObjeto();
            
            if (d != null) {
                DirectorService servicio = new DirectorService();
                /// no pude manejar los privilegios para los otros verbos y decidi realizar un objeto que contenga la acción y el objeto de muestra
                
                switch (pDto.getMetodo().toUpperCase()) {
                    case "POST":
                        if (!servicio.crear(d)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo crear el director %s"}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Director %s creado con exito."}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_CREATED);
                        }
                        break;
                    case "DELETE":
                        if (!servicio.eliminarDirector(d.getId_director())) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo borrar el director %s."}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"director %s eliminada con exito."}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_OK);
                        }
                        break;
                    case "PUT":
                        if (servicio.actualizarDirector(d)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo actualizar el director %s."}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);

                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Director %s actualizado con exito."}
                                             """.formatted(d.getNombre() + " " + d.getApellido()));
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
            DirectorService ds = new DirectorService();
            ObjectMapper om = new ObjectMapper();
            boolean fallo = false;
            String mensajeFallo = "";
            ArrayList<DirectorDTO> d = null;
            switch (uri) {
                case "all":
                    d = ds.obtenerDirectores();
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
                            d = ds.obtenerPorColumnaValor(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0]);
                            break;
                        case 3:
                            d = ds.obtenerPorColumnaValor(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0], req.getParameterValues("caso")[0]);
                            break;
                    }
                    break;
                default:
                    if (esNumerico(uri)) {
                        d = new ArrayList();
                        d.add(ds.obtenerDirectorPorID(Long.valueOf(uri)));
                    }
                    ;
            }
            if (fallo) {
                resultado = formatedResult(0, mensajeFallo);
            } else {
                System.out.println(d);
                resultado = formatedResult(d == null ? 0 : d.get(0) == null ? 0 : d.size(), om.writeValueAsString(d));
            }
        }
        resp.getWriter().println(resultado);
    }
}
