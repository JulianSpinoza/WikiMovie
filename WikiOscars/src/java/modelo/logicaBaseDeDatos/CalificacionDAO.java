/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logicaBaseDeDatos;

import modelo.entity.Calificacion;
import modelo.entity.CalificacionPK;
import modelo.entity.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduwin
 */
public class CalificacionDAO {

    public static ResultSet resultado;

    public int ObtenerUltimaCalificacion() {
        Connection con = ConexionBD.devolverConexion();
        int ultimoId = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Max(idCalificacion) from Calificacion");

            while (rs.next()) {

                ultimoId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return ultimoId;

    }
    
    public int CantidadCalificacion(int pelicula) {
        Connection con = ConexionBD.devolverConexion();
        int ultimacalificacion = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Calificacion\n" +
            "where Calificacion.idPeliculafk ="+pelicula+";");

            while (rs.next()) {

                ultimacalificacion = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return ultimacalificacion;

    }
    

    public int idusuariofk(String nickname) {
        Connection con = ConexionBD.devolverConexion();
        int idusuariofk = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct U.idUsuario from Usuario U \n"
                        + "where U.Nickname = '" + nickname + "';");

            while (rs.next()) {

                idusuariofk = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return idusuariofk;
    }

    public void CrearCalificacion(Calificacion calificacion, CalificacionPK calificacionPK, String nickname) {
        int ultimoID = ObtenerUltimaCalificacion();
        int idCalificaciocreada = 0;
        if (ultimoID > 0) {
            idCalificaciocreada = ultimoID + 1;
            Connection con = ConexionBD.devolverConexion();
            try {
                  int idusuario = 0;

                
                idusuario= idusuariofk(nickname);
                Statement st = con.createStatement();

                st.executeUpdate("INSERT INTO Calificacion (idCalificacion, idPeliculafk, iidUsuariofk, valor)  VALUES (" + idCalificaciocreada + ", '" + calificacionPK.getIdPeliculafk() + "', '" + idusuario + "', " + 1 + "); ");

            } catch (SQLException ex) {
                Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
            }
        }
    }

    public void BorrarCalificacion(Calificacion calificacion, CalificacionPK calificacionPK, String nickname) {

        Connection con = ConexionBD.devolverConexion();
        try {
             int idusuario = 0;

                
                idusuario= idusuariofk(nickname);
                Statement st = con.createStatement();

            st.executeUpdate("DELETE  FROM Calificacion where iidUsuariofk =" + idusuario + " AND idPeliculafk = " + calificacionPK.getIdPeliculafk() + ";");

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        
 }

    public void cerrarBD() {
        ConexionBD.cerrarConexion();
    }

}
