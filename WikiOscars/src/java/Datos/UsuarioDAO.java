/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduwin
 */
public class UsuarioDAO {
    private Usuario u;
    /*
    public void salvar(){
        Connection con = ConexionBD.devolverConexion();
        try {
            String query = "UPDATE Persona " +
"SET nombre = ?, apellido = ?, edad = ? "+
" WHERE ID = ?;";
            
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, p.getNombre());
            st.setString(2, p.getApellido());
            st.setInt(3, p.getEdad());
            st.setInt(4, p.getID());
            
            
            st.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
    }
    
    */
    public Usuario buscarID(int ID){
        Connection con = ConexionBD.devolverConexion();
        Usuario usuario = new Usuario();
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from Usuario where idUsuario="+ID);
            
            while(rs.next()){
                //this.p = new Persona(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getInt(4));
                //this.p = new Persona(, rs.getNString(2), , );
                int a = 0;
                 a = rs.getInt(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                
                
                usuario.setIdUsuario(a);
                usuario.setCorreo(b);
                usuario.setContraseña(c);
                usuario.setNickname(d);
                
            }   
            
        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public int ObtenerUltimoIdUsuario(){
        Connection con = ConexionBD.devolverConexion();
        Usuario usuario = new Usuario();
        int ultimoId = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select Max(idUsuario)+1 from Usuario");
            
            while(rs.next()){
                //this.p = new Persona(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getInt(4));
                //this.p = new Persona(, rs.getNString(2), , );
                ultimoId = rs.getInt(1);        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return ultimoId;
    }
    
    public int CrearPersona(Usuario usuario){
        int ultimoID = ObtenerUltimoIdUsuario();        
        int idUsuarioCreado = 0;
        if(ultimoID > 0 ){
            idUsuarioCreado = ultimoID +1;
            Connection con = ConexionBD.devolverConexion();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO Usuario (idUsuario, Correo, Contraseña, Nickname)  VALUES ("+ idUsuarioCreado +", '"+ usuario.getCorreo() +"', '"+ usuario.getContraseña() +"', '"+ usuario.getNickname() +"'); ");

               // while(rs.next()){
                    //this.p = new Persona(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getInt(4));
                    //this.p = new Persona(, rs.getNString(2), , );
               //     int a = 0;
               //     idUsuarioCreado = rs.getInt(1);              

               // }

            } catch (SQLException ex) {
                Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
            }        
        }
        return idUsuarioCreado;
    }
    
    public int RetornarPersona(Usuario usuario){
        int ultimoID = ObtenerUltimoIdUsuario();        
        int idUsuarioCreado = 0;
        if(ultimoID > 0 ){
            idUsuarioCreado = ultimoID +1;
            Connection con = ConexionBD.devolverConexion();
            try {
                Statement st = con.createStatement();
                st.executeQuery("select * from Usuario where upper(Usuario.Nickname) like '%JOSE' AND UPPER (Usuario.Contraseña) like '%PELICULAS';");
                
                
                               // while(rs.next()){
                    //this.p = new Persona(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getInt(4));
                    //this.p = new Persona(, rs.getNString(2), , );
               //     int a = 0;
               //     idUsuarioCreado = rs.getInt(1);              

               // }

            } catch (SQLException ex) {
                Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
            }        
        }
        return idUsuarioCreado;
    }
    
    
    
    public void cerrarBD(){
        ConexionBD.cerrarConexion();
    }
    
}
