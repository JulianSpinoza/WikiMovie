/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logicaBaseDeDatos;

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
public class UsuarioDAO {

    public static ResultSet resultado;

    private Usuario u;


    public int ObtenerUltimoIdUsuario() {
        Connection con = ConexionBD.devolverConexion();
        Usuario usuario = new Usuario();
        int ultimoId = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Max(idUsuario) from Usuario");

            while (rs.next()) {

                ultimoId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return ultimoId;

    }

    public int CrearPersona(Usuario usuario) {
        int ultimoID = ObtenerUltimoIdUsuario();
        int idUsuarioCreado = 0;
        if (ultimoID > 0) {
            idUsuarioCreado = ultimoID + 1;
            Connection con = ConexionBD.devolverConexion();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO Usuario (idUsuario, Correo, Contraseña, Nickname)  VALUES (" + idUsuarioCreado + ", '" + usuario.getCorreo() + "', '" + usuario.getContraseña() + "', '" + usuario.getNickname() + "'); ");

            } catch (SQLException ex) {
                Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
            }
        }
        return idUsuarioCreado;
    }

    public int VerificarPersona(Usuario usuario) throws SQLException {

        Connection con = ConexionBD.devolverConexion();
        int resultadoInt = 0;
        try {
            Statement st = con.createStatement();
            resultado = st.executeQuery("select * from Usuario where (Usuario.Nickname) = '" + usuario.getNickname() + "' OR (Usuario.Correo) = '" + usuario.getCorreo() + "';");

            if (resultado.next()) {

                resultadoInt = 1;

            } else {

                resultadoInt = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return resultadoInt;

    }

    public int RetornarPersona(Usuario usuario) throws SQLException {

        Connection con = ConexionBD.devolverConexion();
        int resultadoInt = 0;
        try {
            Statement st = con.createStatement();
            resultado = st.executeQuery("select * from Usuario where (Usuario.Nickname) = '" + usuario.getNickname() + "' AND (Usuario.Contraseña) = BINARY '" + usuario.getContraseña() + "';");

            if (resultado.next()) {

                resultadoInt = 1;

            } else {
                System.out.println("No listo");

                resultadoInt = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger("BaseDeDatos.class.getName()").log(Level.SEVERE, null, ex);
        }
        return resultadoInt;

    }

    public void cerrarBD() {
        ConexionBD.cerrarConexion();
    }

}
