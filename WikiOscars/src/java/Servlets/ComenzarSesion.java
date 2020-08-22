/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import EntityClassses.Usuario;
import RestFull.service.UsuarioFacadeREST;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USERS
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class ComenzarSesion extends HttpServlet {
    String correo,password,id,nickname;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
         public String Correo,password1,Nickname;
         public static int idUser;
        
      UsuarioFacadeREST useremf= new UsuarioFacadeREST();
      public int comprobar(String comprocorreo,String compropassword){
              int a=0;
              for(int i=1;i<=useremf.count();i++){
                  Usuario user = new Usuario(i);
                  
                  if(comprocorreo==user.getCorreo()&&compropassword==user.getContraseña()){
                     a=i;  
                  }
              }
              return a;   
        }
      public void getidReciente(){
          idUser =(useremf.count());
      }
     
      
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>oscars</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"Estilos/PrincipalPage.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"vacio\">");
            out.println("<div id=\"logo\">");
            out.println("</div>");          
            out.println("<div id=\"menu\">");
            out.println("<form action=\"IniciarSesion\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"Registrarme\">");
            out.println("<input type=\"submit\" value=\"Iniciar sesión\">");
            out.println("</form>");
            out.println("</div> "); 
            out.println("</div>");                     
            out.println("<div class=\"contenedor\">");           
            out.println("<section>");
            out.println("<h2>");
            out.println("Mejores Peliculas Nominadas 2020");
            out.println("</h2>");
            out.println("<article>");
            out.println("<h3>");
            out.println("Once Upon a Time in Hollywood");
            out.println("</h3>");
            out.println("<p id=\"imagen1\">");
            out.println("</p>");
            out.println("<input class=\"btnOUTH\" type=\"button\" value=\"Visto\">");
            out.println("<input class=\"btnOUTH\" type=\"button\" value=\"Me gusta\">");
            out.println("<input class=\"btnOUTH\" type=\"button\" value=\"No me gusta\">");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("En Los Ángeles en 1969, la envejecida estrella de televisión Rick Dalton y su doble de acrobacias Cliff Booth luchan por abrirse camino en una industria y una ciudad que ya casi no reconocen.");
            out.println("</p>");
            out.println("</article>");

            out.println("<article>");
            out.println("<h3>");
            out.println("Jojo Rabbit");
            out.println("</h3>");
            out.println("<p id=\"imagen2\">");
            out.println("</p>");
            out.println("<input class=\"btnJJR\" type=\"button\" value=\"Visto\">");
            out.println("<input class=\"btnJJR\" type=\"button\" value=\"Me gusta\">");
            out.println("<input class=\"btnJJR\" type=\"button\" value=\"No me gusta\">");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("Durante la Segunda Guerra Mundial, el mundo de un niño alemán solitario se pone patas arriba cuando descubre que su madre soltera esconde a una joven judía en su ático. Ayudado únicamente por su idiota amigo imaginario Adolf Hitler, Jojo debe enfrentarse a su nacionalismo ciego.");
            out.println("</p>");
            out.println("</article>");

            out.println("<article>");
            out.println("<h3>");
            out.println("Marriage Story");
            out.println("</h3>");
            out.println("<p id=\"imagen3\">");
            out.println("</p>");
            out.println("<input class=\"btnMS\" type=\"button\" value=\"Visto\">");
            out.println("<input class=\"btnMS\" type=\"button\" value=\"Me gusta\">");
            out.println("<input class=\"btnMS\" type=\"button\" value=\"No me gusta\">");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("Una joven pareja creativa y su hijo navegan por las complicadas aguas de la separación entre dos costas y el inminente divorcio.");
            out.println("</p>");
            out.println("</article>");

            out.println("<article>");
            out.println("<h3>");
            out.println("The Irishman");
            out.println("</h3>");
            out.println("<p id=\"imagen4\">");
            out.println("</p>");
            out.println("<input class=\"btnIMAN\" type=\"button\" value=\"Visto\">");
            out.println("<input class=\"btnIMAN\" type=\"button\" value=\"Me gusta\">");
            out.println("<input class=\"btnIMAN\" type=\"button\" value=\"No me gusta\">");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("De anciano, el veterano de la Segunda Guerra Mundial Frank Sheeran reflexiona sobre su vida como estafador y sicario de la mafia, trabajando junto a muchas figuras notorias, incluido Jimmy Hoffa, el tema de uno de los mayores misterios sin resolver en la historia de Estados Unidos.");
            out.println("</p>");
            out.println("</article>");


            out.println("<article>");
            out.println("<h3>");
            out.println("Parasite");
            out.println("</h3>");
            out.println("<p id=\"imagen5\">");
            out.println("</p>");
            out.println("<input class=\"btnPA\" type=\"button\" value=\"Visto\">");
            out.println("<input class=\"btnPA\" type=\"button\" value=\"Me gusta\">");
            out.println("<input class=\"btnPA\" type=\"button\" value=\"No me gusta\">");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("La codicia, la discriminación de clase y un intruso misterioso amenazan la relación simbiótica recién formada entre la acaudalada familia Park y el indigente clan Kim.");
            out.println("</p>");
            out.println("</article>");
            out.println("</section>   ");
            out.println("</div>");
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
