/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import servicios.UsersServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {

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
        
        UsersServicios us = new UsersServicios();
        String op = request.getParameter("op");

        try {
            if (op != null) {
                String nombre = request.getParameter("nombre");
                String pass = request.getParameter("pass");
                
                User usuario = new User();
                usuario.setName(nombre);
                usuario.setPassword(pass);
                
                String respuesta = null;
               
                switch (op) {
                    case "listarUsuarios":
                        request.setAttribute("users", us.getAllUsers());//llamada a servicios

                        break;

                    case "anadirUsuarios":
                        usuario = us.anadirUser(usuario);//llamada a servicios
                        
                        if(usuario != null){
                            request.setAttribute("mensajeUser", "mal");
                        }
                        request.setAttribute("mensajeUser", "bien");

                        break;
                    
                    case "actualizarUsuarios":
                        usuario = us.actualizarUser(usuario);//llamada
                        
                        if(usuario != null){
                            request.setAttribute("mensajeUser", "mal");
                        }
                        request.setAttribute("mensajeUser", "bien");

                        break;

                    case "borrarUsuarios":
                        respuesta = us.borrarUser(usuario);//llamada
                        
                        if(respuesta != null){
                            request.setAttribute("mensajeUser", "bien");
                        }
                        request.setAttribute("mensajeUser", "mal");

                        break;

                    case "borrar2Usuarios":
                        respuesta = us.borrarUser2(usuario);//llamada
                        if(respuesta != null){
                            request.setAttribute("mensajeUser", "bien");
                        }
                        request.setAttribute("mensajeUser", "mal");

                        break;
                        
                }

            }
            request.getRequestDispatcher("pintarUsers.jsp").forward(request, response);
            
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                
                request.setAttribute("error", "ApiKey erronea");
               
            } else {
                request.setAttribute("error", "Ha ocurrido un error");
            }

            if (op == null) {
                request.getRequestDispatcher("pintarUsers.jsp").forward(request, response);
            }
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
