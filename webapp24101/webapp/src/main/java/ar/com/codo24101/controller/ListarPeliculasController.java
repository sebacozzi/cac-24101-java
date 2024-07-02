package ar.com.codo24101.controller;

import ar.com.codo24101.dto.MovieDTO;
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
import java.util.List;


@WebServlet("/peliculas/*")
public class ListarPeliculasController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        vistaParametros(req);
        String uri = req.getRequestURI().split("/")[req.getRequestURI().split("/").length - 1];
        System.out.println("Uri ::::" + uri);
        String metodo = req.getMethod();
        String resultado = """
                           {"count":%d,"result":%s}""";
        String movies = "{}";

        if ("GET".equals(metodo)) {
            ObjectMapper om = new ObjectMapper();
            MovieService ms = new MovieService();
            if (esNumerico(uri)) {
                ArrayList<MovieDTO> m = new ArrayList<>();
                m.add(ms.obtener(Long.parseLong(uri)));
                if (m.get(0) == null) {
                    movies = resultado.formatted(0, "[]");
                } else {
                    movies = resultado.formatted(m.size(), om.writeValueAsString(m));
                }
            } else if (uri.equals("all")) {

                ArrayList<MovieDTO> m = ms.obtenerPeliculas();

                movies = resultado.formatted(m.size(), om.writeValueAsString(m));
            } else if (uri.equals("filtro")) {
                ArrayList<MovieDTO> m = null;
                switch (validaParmetros(req.getParameterNames())) {
                    case -1:
                        movies = resultado.formatted(0, "[Parametros Invialidos]");
                        break;
                    case 1:
                         movies = resultado.formatted(0, "[La cantidad de parametros es incorrecto]");
                        break;
                    case 2:
                        m = ms.obtenerByVal(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0]);
                        movies = resultado.formatted(m.size(), om.writeValueAsString(m));
                        break;
                    case 3:
                        m = ms.obtenerByVal(req.getParameterValues("columnas")[0], req.getParameterValues("valores")[0], req.getParameterValues("caso")[0]);
                        movies = resultado.formatted(m.size(), om.writeValueAsString(m));
                        break;
                }
            } else {
                movies = resultado.formatted(0, "[]");
            }

        }

        resp.getWriter().println(movies);
    }

    private boolean esNumerico(String numero) {
        try {
            Long.valueOf(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int validaParmetros(Enumeration<String> parametros) {
        
        List<String> validos = List.of("columnas", "valores", "caso");
        //List<String> validos = List.of("id_movie", "nombre", "descripcion", "genero", "calificacion", "anio", "estrellas", "director");
        int ret = 0;
        int check = 0;
        while (parametros.hasMoreElements()) {
            String pan = parametros.nextElement();
            if (validos.contains(pan)) {
                ret++;
            }
            check++;
        }
        return ret == check ? ret : -1;
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
        System.out.println("*".repeat(15) + "doGet en ListarPeliculasController" + "*".repeat(15));
    }
}
