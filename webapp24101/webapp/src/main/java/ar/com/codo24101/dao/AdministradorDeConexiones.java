package ar.com.codo24101.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {

    
      public static Connection conectar() {
      String url="jdbc:mysql://127.0.0.1:3306/peliculas";
      String user = "root";//System.getenv("user_mysql");
      String password = "root";//System.getenv("pass_mysql");
      String driver = "com.mysql.cj.jdbc.Driver";
      Connection con=null;
      //System.
          try {
              
              Class.forName(driver);
              con = DriverManager.getConnection(url,user,password) ;
          } catch (Exception e) {
              System.out.println("Se produjo una falla en conexión. "+e);
          }
      
      return con;
      }
      
      public static void desconectar(Connection con){
          try {
            con.close(); 
          } catch (Exception e) {
              System.out.println("Se produjo una falla al cerrar la conexión. "+ e.getMessage());
          }
      }
     
}
