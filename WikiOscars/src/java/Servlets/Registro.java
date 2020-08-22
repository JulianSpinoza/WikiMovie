/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import EntityClassses.Usuario;
import RestFull.service.UsuarioFacadeREST;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USERS
 */
public class Registro extends ComenzarSesion{
    
    private int ID;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioFacadeREST xd = new UsuarioFacadeREST();
        xd.create(new Usuario(ID++,request.getParameter("correo"),
                request.getParameter("contraseña"),request.getParameter("nick")));
    }
    
}
