/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logicaBaseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduwin
 */
public class PeliculasDAO {


    public ArrayList<Integer> ObtenerPeliculasPorLoginUsuario(String loginUsuario) {
        Connection con = ConexionBD.devolverConexion();
        ArrayList<Integer> peliculasList = new ArrayList<Integer>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select P.idPelicula from Pelicula P, Calificacion C, Usuario U \n"
                    + "where C.idPeliculafk = P.idPelicula and\n"
                    + "U.idUsuario = C.iidUsuariofk and\n"
                    + "C.Valor = 1 and\n"
                    + "U.Nickname = '" + loginUsuario.trim() + "';");

            while (rs.next()) {

                int peliculaId = rs.getInt(1);
                peliculasList.add(peliculaId);
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return peliculasList;
    }

 
    public void cerrarBD() {
        ConexionBD.cerrarConexion();
    }

}
