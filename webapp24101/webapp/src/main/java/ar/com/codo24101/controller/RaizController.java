package ar.com.codo24101.controller;

import ar.com.codo24101.Interfaces.GenericResults;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class RaizController extends HttpServlet implements GenericResults {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().split("/")[req.getRequestURI().split("/").length - 1];
        System.out.println("Uri ::::" + uri);
        
        String main="";
        if (uri.equals("webapp")) {
            main = nullURL();
        }
        resp.getWriter().println(main);
    }

}
