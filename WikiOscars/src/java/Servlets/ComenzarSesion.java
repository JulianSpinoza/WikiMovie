/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import service.CalificacionFacadeREST;
import entity.Usuario;
import Datos.UsuarioDAO;
import service.UsuarioFacadeREST;
import entity.Calificacion;
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
 * @author USERS
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class ComenzarSesion extends HttpServlet {
  
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String correo,password,nickname;
    public String invisible[][] = new String [2][5];
    public static int idReciente=0;
        
    public UsuarioFacadeREST useremf = new UsuarioFacadeREST();
    public CalificacionFacadeREST calificaemf = new CalificacionFacadeREST();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Oscars</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"Estilos/PrincipalPage.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"vacio\">");
            out.println("<div id=\"logo\">");
            out.println("</div>");
            out.println("<div id=\"menu\">");
            out.println("<label>"+nickname+"</label>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"contenedor\">  ");
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
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[0][0]+"\" value=\"Me gusta Once Upon a Time in Hollywood\" name=\"OUTHlike\">");
            out.println("</form>");
            out.println("<form action=\"MeGusta\" method=\"get\">");
            out.println("<input type=\""+invisible[1][0]+"\" value=\"Ya no me gusta Once Upon a Time in Hollywood\" name=\"OUTHdislike\">");
            out.println("</form>");
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
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[0][1]+"\" value=\"Me gusta Jojo Rabbit\" name=\"JORTlike\">");
            out.println("</form>");
            out.println("<form action=\"MeGusta\" method=\"get\">");
            out.println("<input type=\""+invisible[1][1]+"\" value=\"Me gusta Jojo Rabbit\" name=\"JORTdislike\">");
            out.println("</form>");
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
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[0][2]+"\" value=\"Me gusta Marriage Story\" name=\"MSlike\">");
            out.println("</form>");
            out.println("<form action=\"MeGusta\" method=\"get\">");
            out.println("<input type=\""+invisible[1][2]+"\" value=\"Me gusta Marriage Story\" name=\"MSdislike\">");
            out.println("</form>");
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
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[0][3]+"\" value=\"Me gusta The Irishman\" name=\"IRISlike\">");
            out.println("</form>");
            out.println("<form action=\"MeGusta\" method=\"get\">");
            out.println("<input type=\""+invisible[1][3]+"\" value=\"Me gusta The Irishman\" name=\"IRISdislike\">");
            out.println("</form>");
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
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[0][4]+"\" value=\"Me gusta Parasite\" name=\"PARISlike\">");
            out.println("</form>");
            out.println("<form action=\"MeGusta\" method=\"post\">");
            out.println("<input type=\""+invisible[1][4]+"\" value=\"Me gusta Parasite\" name=\"PARISdislike\">");
            out.println("</form>");
            out.println("<h4>");
            out.println("Sinopsis");
            out.println("</h4>");
            out.println("<p class=\"contenido\">");
            out.println("La codicia, la discriminación de clase y un intruso misterioso amenazan la relación simbiótica recién formada entre la acaudalada familia Park y el indigente clan Kim.");
            out.println("</p>");
            out.println("</article>");
            out.println("</section>");

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
        
        password = request.getParameter("password");
        nickname = request.getParameter("nickname");
        
        if(comprobar(nickname,password)==0){
            //colocar processRequest de error
        }else{
 
        Usuario aux = useremf.find(comprobar(nickname,password));
        ArrayList<Calificacion> listaCalificados = (ArrayList<Calificacion>) aux.getCalificacionList();
        
        for(int i=0;i<listaCalificados.size();i++){
            if(listaCalificados.get(i).getValor() == false){
                invisible[0][i]="submit";
                invisible[1][i]="hidden";
            }
            if(listaCalificados.get(i).getValor() == true){
                invisible[0][i]="hidden";
                invisible[1][i]="submit";
            }
        }
        processRequest(request, response);
        }
         
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
        
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        
        
        
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuario.setCorreo(correo);
        usuario.setContraseña(password);
        usuario.setNickname(nickname);
        
        int idUsuarioCreado = usuarioDAO.CrearPersona(usuario);
        
        
        /*setidReciente();
        useremf.create(new Usuario(idReciente++,request.getParameter("correo"),
                request.getParameter("password"),request.getParameter("nickname")));
        
        for(int i=0;i<5;i++){
            Calificacion aux = new Calificacion(0,i);
            aux.setValor(false);
            calificaemf.create(aux);
        }
        */
        
        response.setContentType("text/html;charset=UTF-8");        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Comenzar Sesión</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comenzar Sesion at </h1>");
            if( idUsuarioCreado > 0 ){                
                out.println("<h1>ID USUARIO CREADO: " + idUsuarioCreado +"</h1>");
                out.println("<h2>Usuario:  getCorreo " + usuario.getCorreo() +" getContraseña " + usuario.getContraseña()+" getNickname " + usuario.getNickname()+" </h2>" );
            } else {
                out.println("<h1>Error Creando usuario.</h1>");
                
            }
            out.println("</body>");
            out.println("</html>");
        }
        
        processRequest(request, response);
        correo = request.getParameter("correo");
        password = request.getParameter("password");
        nickname = request.getParameter("nickname");
        setidReciente();
        Usuario user = new Usuario(idReciente++,correo, password, nickname);
        useremf.create(user);
//        
//        for(int i=0;i<5;i++){
//            Calificacion aux = new Calificacion(0,i);
//            aux.setValor(false);
//            aux.setIidUsuariofk(user);
//            calificaemf.create(aux);
//        }
//        
//        for(int j=0;j<5;j++){
//            invisible[0][j] = "submit";
//            invisible[1][j] = "hidden";
//        }
        
//        processRequest(request, response);
        prueba(request, response,request.getParameter("correo"),request.getParameter("password"),request.getParameter("nickname"));
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

    //Methods
    public void prueba(HttpServletRequest request, HttpServletResponse response,String correo, String password, String nickname)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Oscars</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"Estilos/PrincipalPage.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"vacio\">");
            out.println("<div id=\"logo\">");
            out.println("</div>");
            out.println("<div id=\"menu\">");
            out.println("<label>"+nickname+"</label>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"contenedor\">  ");
            
            out.println(correo+","+ password);
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }
    }
    public int comprobar(String compronick,String compropassword){
              int a=0;
              for(int i=1;i<=useremf.count();i++){
                  Usuario user = new Usuario(i);
                  
                  if((compronick.equals(user.getNickname())) && (compropassword.equals(user.getContraseña()))){
                     a=i;  
                  }
              }
              return a;   
    }
    
    public void setidReciente(){
        idReciente =(useremf.count());
    }
}
