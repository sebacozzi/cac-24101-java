package ar.com.codo24101.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AdministradorDeConexiones {

    final private static String TOLOG = "%s - %s %s.";
    private static Connection con = null;
    private static Statement stat = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/peliculas";
    private static final String USER = "root";//System.getenv("user_mysql");
    private static final String PASSWORD = "root";//System.getenv("pass_mysql");
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void conectar() {
        //Connection con = null;
        //System.
        try {
            
            Class.forName(DRIVER);
            log(true,"Driver MySql cargado");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            log(true,"Conexión creada");
        } catch (Exception e) {
            System.out.println("Se produjo una falla en conexión. " + e);
        }
    }

    public static void desconectar() {
        try {
            if (stat != null) {
                stat.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Se produjo una falla al cerrar la conexión. " + e.getMessage());
        }
    }

    public static boolean genericoCrearActualizarBorrar(String sql) throws Exception {
        final String aRealizar = sql.split(" ")[0];
        boolean ret=false;
        try {
            conectar();
            stat = con.createStatement();
            ret = stat.execute(sql);
            ret = (aRealizar.equalsIgnoreCase("DELETE") ? true : ret);
            log(ret, aRealizar);

        } catch (Exception e) {
            System.out.println("Se produjo una falla en " + aRealizar + ". " + e);
        } finally {
            desconectar();
            return ret;
        }
    }

    public static ResultSet genericoConsulta(String sql) throws Exception {
        final String aRealizar = sql.split(" ")[0];

        ResultSet resultado = null;

        try {
            conectar();
            stat = con.createStatement();
            resultado = stat.executeQuery(sql);

            log(resultado != null, aRealizar);
            //TODO::: requiere desconectar en el metodo que lo llama
        } catch (Exception e) {
            System.out.println("Se produjo una falla en " + aRealizar + ". " + e);
        }
        return resultado;
    }

    
    private static String getFecha() {
        return new SimpleDateFormat("dd/mm/yyyy - hh:mm:ss").format(new Date());
    }

    private static void log(Boolean verifica, String aRealizar) {
        String fecha = getFecha();
        
        System.out.println(TOLOG.formatted(fecha, aRealizar, (verifica ? "realizado.": "no realizado.")));
     
    }
}
