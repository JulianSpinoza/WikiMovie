/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresServlets;

import modelo.logicaBaseDeDatos.CalificacionDAO;
import modelo.logicaBaseDeDatos.Html;
import modelo.logicaBaseDeDatos.PeliculasDAO;
import modelo.entity.Calificacion;
import modelo.entity.CalificacionPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduwin
 */
@WebServlet(name = "CrearCalificacion", urlPatterns = {"/CrearCalificacion"})
public class CrearCalificacion extends HttpServlet {

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
            out.println("<title>Servlet Calificaciones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calificaciones at " + request.getContextPath() + "</h1>");
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
        String nickname = request.getParameter("nickname");
        int peliculaid = Integer.parseInt(request.getParameter("peliculaid"));

        Calificacion calificacion = new Calificacion();
        CalificacionPK calificacionPK = new CalificacionPK();
        CalificacionDAO calificacionDAO = new CalificacionDAO();

        int calificacionid = calificacionDAO.ObtenerUltimaCalificacion() + 1;

        calificacionPK.setIdCalificacion(calificacionid);
        calificacionPK.setIdPeliculafk(peliculaid);

        calificacionDAO.CrearCalificacion(calificacion, calificacionPK, nickname);
       
        int totalPelicula1=calificacionDAO.CantidadCalificacion(1);
        int totalPelicula2=calificacionDAO.CantidadCalificacion(2);
        int totalPelicula3=calificacionDAO.CantidadCalificacion(3);
        int totalPelicula4=calificacionDAO.CantidadCalificacion(4);
        int totalPelicula5=calificacionDAO.CantidadCalificacion(5);




        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            PeliculasDAO peliculasDAO = new PeliculasDAO();

            ArrayList<Integer> peliculasMeGusta = peliculasDAO.ObtenerPeliculasPorLoginUsuario(nickname);
            out.println(html.pantalla(peliculasMeGusta, nickname, totalPelicula1, totalPelicula2, totalPelicula3, totalPelicula4, totalPelicula5));
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
