package ar.com.codo24101.controller;

import ar.com.codo24101.Interfaces.GenericResults;
import ar.com.codo24101.Interfaces.Validaciones;
import ar.com.codo24101.dto.MovieDTO;
import ar.com.codo24101.dto.MovieCRUDDTO;
import ar.com.codo24101.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/peliculas/*")
public class PeliculasController extends HttpServlet implements GenericResults, Validaciones {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (puedePost(resp.getHeader("Access-Control-Allow-Methods"))) {
        
            ObjectMapper om = new ObjectMapper();

            MovieCRUDDTO pDto = om.readValue(req.getReader(), MovieCRUDDTO.class);
            
            MovieDTO m = pDto.getObjeto();
            
            if (m != null) {
                MovieService servicio = new MovieService();
                /// no pude manejar los privilegios para los otros verbos y decidi realizar un objeto que contenga la acción y el objeto de muestra
                
                switch (pDto.getMetodo().toUpperCase()) {
                    case "POST":
                        if (!servicio.crear(m)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo crear la pelicula %s"}
                                             """.formatted(m.getNombre()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Pelicula %s creada con exito."}
                                             """.formatted(m.getNombre()));
                            resp.setStatus(HttpServletResponse.SC_CREATED);
                        }
                        break;
                    case "DELETE":
                        if (!servicio.eliminarPelicula(m.getId_movie())) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo borrar la pelicula %s."}
                                             """.formatted(m.getNombre()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Pelicula %s eliminada con exito."}
                                             """.formatted(m.getNombre()));
                            resp.setStatus(HttpServletResponse.SC_OK);
                        }
                        break;
                    case "PUT":
                        if (servicio.actualizarPelicula(m)) {
                            resp.getWriter().println("""
                                             {"resultado":"No se pudo actualizar la pelicula %s."}
                                             """.formatted(m.getNombre()));
                            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);

                        } else {
                            resp.getWriter().println("""
                                             {"resultado":"Pelicula %s actualizada con exito."}
                                             """.formatted(m.getNombre()));
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
        String metodo = req.getMethod();
        String movies = "";

        if (puedeGet(metodo)) {
            ObjectMapper om = new ObjectMapper();
            MovieService ms = new MovieService();
            if (esNumerico(uri)) {
                ArrayList<MovieDTO> m = new ArrayList<>();
                m.add(ms.obtener(Long.valueOf(uri)));
                if (m.get(0) == null) {
                    movies = formatedResult(0, "[]");
                } else {
                    movies = formatedResult(m.size(), om.writeValueAsString(m));
                }
            } else if (uri.equals("all")) {

                ArrayList<MovieDTO> m = ms.obtenerPeliculas();

                movies = formatedResult(m.size(), om.writeValueAsString(m));
            } else if (uri.equals("filtro")) {
                ArrayList<MovieDTO> m = null;
                switch (validaParametros(req.getParameterNames())) {
                    case -1:
                        movies = formatedResult(0, "[Parametros Invialidos]");
                        break;
                    case 1:
                        movies = formatedResult(0, "[La cantidad de parametros es incorrecto]");
                        break;
                    case 2:
                        m = ms.obtenerByVal(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0]);
                        movies = formatedResult(m.size(), om.writeValueAsString(m));
                        break;
                    case 3:
                        m = ms.obtenerByVal(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0], req.getParameterValues("caso")[0]);
                        movies = formatedResult(m.size(), om.writeValueAsString(m));
                        break;
                }
            } else {
                movies = formatedResult(0, "[]");
            }
        }
        resp.getWriter().println(movies);
    }

    private void vistaParametros(HttpServletRequest req) {
        System.out.println("");
        System.out.println("*".repeat(15) + "doGet en ListarPeliculasController" + "*".repeat(15));
        Enumeration<String> en = req.getParameterNames();
        //req.getHeader()
        System.out.println("-".repeat(50));
        System.out.println("Request URL: " + req.getRequestURL().toString());
        System.out.println("Context Path: " + req.getContextPath());
        System.out.println("Translated Path: " + req.getPathTranslated());
        System.out.println("Server Name: " + req.getServerName());
        System.out.println("Query String: " + req.getQueryString());
        System.out.println("Request URI: " + req.getRequestURI());
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        System.out.println("Parameters Names:");
        System.out.println("-".repeat(50));
        while (en.hasMoreElements()) {
            String val = en.nextElement();
            System.out.println("Nombre Atributo: " + val + " :".repeat(5) + " Valor: " + req.getParameterValues(val)[0] + " :".repeat(5) + " Longitud Valor: " + req.getParameterValues(val).length);
        }
        System.out.println("-".repeat(50));
        System.out.println("");
        en = req.getHeaderNames();
        System.out.println("-".repeat(50));
        System.out.println("Headers Names:");
        System.out.println("-".repeat(50));

        while (en.hasMoreElements()) {
            String val = en.nextElement();
            System.out.println("Nombre: " + val + " :".repeat(5) + " Valor: " + req.getHeader(val));
        }

        System.out.println("-".repeat(50));
        System.out.println("Metodo::  " + req.getMethod());
        System.out.println("");
        System.out.println("*".repeat(15) + "doGet en ListarPeliculasController" + "*".repeat(15));
    }
}
