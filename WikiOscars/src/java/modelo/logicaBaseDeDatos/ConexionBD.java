/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logicaBaseDeDatos;

/**
 *
 * @author Eduwin
 */
import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sehjud
 */
public class ConexionBD {

    private static Connection db = null;
    private String driver = "jdbc:mysql";
    private String url = "db-prog-avanzada-g81.cq73qyc2lysx.us-east-1.rds.amazonaws.com";
    private int puerto = 3306;
    private String bd = "WikiMovie";

    private ConexionBD() {
        if (db == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                db = (Connection) DriverManager.getConnection(driver + "://" + url + ":" + puerto + "/" + bd, "prog_avanzada", "pa2020-1");
                if (db != null) {
                    System.out.println("Conectado Correctamente");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Connection devolverConexion() {

        if (db == null) {
            new ConexionBD();
        }
        return db;
    }

    public static void cerrarConexion() {
        if (db != null) {
            try {
                db.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
