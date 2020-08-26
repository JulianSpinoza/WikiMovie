/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresServlets;

import modelo.logicaBaseDeDatos.CalificacionDAO;
import modelo.logicaBaseDeDatos.Html;
import modelo.logicaBaseDeDatos.PeliculasDAO;
import modelo.logicaBaseDeDatos.UsuarioDAO;
import modelo.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduwin
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    int retorno;
    Html html = new Html();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Iniciar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Iniciar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");

        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuario.setContraseña(password);
        usuario.setNickname(nickname);

        try {
            retorno = usuarioDAO.RetornarPersona(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            PeliculasDAO peliculasDAO = new PeliculasDAO();
            

            ArrayList<Integer> peliculasMeGusta = peliculasDAO.ObtenerPeliculasPorLoginUsuario(nickname);
                    
            CalificacionDAO caificacionDAO = new CalificacionDAO();
            int totalCalificacion1 = caificacionDAO.CantidadCalificacion(1);
            int totalCalificacion2 = caificacionDAO.CantidadCalificacion(2);
            int totalCalificacion3 = caificacionDAO.CantidadCalificacion(3);
            int totalCalificacion4 = caificacionDAO.CantidadCalificacion(4);
            int totalCalificacion5 = caificacionDAO.CantidadCalificacion(5);

            if (retorno == 1) {
                out.println(html.pantalla(peliculasMeGusta, nickname, totalCalificacion1, totalCalificacion2, totalCalificacion3, totalCalificacion4, totalCalificacion5));
            } else {        
                out.println(html.inicial("Usuario o contraseña invalidos"));
            }

        }

        processRequest(request, response);
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
