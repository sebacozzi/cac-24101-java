/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.service;

import ar.com.codo24101.dao.DAO;
import ar.com.codo24101.Implements.DirectorJDBCMysqlImpl;
import ar.com.codo24101.dto.DirectorDTO;
import java.util.ArrayList;

/**
 *
 * @author Asterisco Sublimados
 */
public class DirectorService {
    private DAO<DirectorDTO> daoD;

    public DirectorService() {
        daoD = new DirectorJDBCMysqlImpl();
    }
    
    public boolean crear(DirectorDTO d){
        daoD.create(d);
        return daoD.getByVal("nombre,apellido", d.getNombre()+","+d.getApellido())!=null;
    }
    public ArrayList<DirectorDTO> obtenerDirectores(){
        return daoD.getLista();
    }
    
    public DirectorDTO obtenerDirectorPorID(Long ID){
        return daoD.getByID("id_director",ID);
    }
    
    public ArrayList<DirectorDTO> obtenerPorColumnaValor(String columnas, String valores){
        return daoD.getByVal(columnas, valores);
    }
    
    public ArrayList<DirectorDTO> obtenerPorColumnaValor(String columnas, String valores,String caso){
        return daoD.getByVal(columnas, valores, caso);
    }

    public boolean eliminarDirector(Long id_director) {
        daoD.delete(id_director);
        return daoD.getByID("id_director", id_director) == null;
    }

    public boolean actualizarDirector(DirectorDTO d) {
        daoD.update(d);
        return true;
    }
}
