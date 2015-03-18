/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utsjr.clases;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class PersonaDAO {
    
    //atributos
    
    Connection con;
    Statement smt;
    ResultSet rs;

    public PersonaDAO() {
    }
    
    
    //metodo  para la conexion BD
    
    public void conectar() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/pruebas",
                    "root","");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    
    
    //metodo para desconectar
    
    public void desconectar()throws SQLException{
        con.close();
    }
    
    
    
    //metodo para obtener todos los registros
    
    public List<Persona> getPersonas()throws SQLException{
        conectar();
        smt=con.createStatement();
        rs=smt.executeQuery("select * from personas");
        List<Persona> listaPersonas=new ArrayList<Persona>();
                
        while(rs.next()){
            Persona p= new Persona();
            p.setIdPersona(rs.getInt("idpersona"));//campos de la base los que
                                                   //estan entre comillas
            p.setNombre(rs.getString("nombre"));
            p.setApellidos(rs.getString("apellidos"));
            p.setSexo(rs.getString("sexo"));
            
            listaPersonas.add(p);
        }
        desconectar();
        return listaPersonas;
    }
    
    
    
    
    
    
    
}
