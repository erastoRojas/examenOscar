/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DeleteForceException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MensajeHttp;
import model.User;
import servicios.UserServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "RestUsers", urlPatterns = {"/rest/RestUsers"})
public class RestUsers extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    UserServicios us = new UserServicios();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Collection listaUsers = us.getAllUsers();//llamada a servicios

        if (listaUsers == null) {
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("Ha ocurrido un error");

            request.setAttribute("json", info);
        } else {
            request.setAttribute("json", listaUsers);
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
        
        User u = (User) request.getAttribute("user");

        if (us.updateUser(u) == false) {//llamada a servicios
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("Ha ocurrido un error");

            request.setAttribute("json", info);
        }else{
            request.setAttribute("json", u);
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User u = (User) request.getAttribute("user");
            
            boolean bole = us.addUser(u);
        
        if (!bole) {//llamada a servicios
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("Ha ocurrido un error");

            request.setAttribute("json", info);
        }else{
            request.setAttribute("json", u);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User u = (User) request.getAttribute("user");
        String accion = (String) request.getAttribute("accion");

        if (accion.equals("borrar2")) {
            try {
                if (us.delUser2(u) ==  false) {
                    response.setStatus(500);
                    MensajeHttp info = new MensajeHttp("Ha ocurrido un error");
                    
                    request.setAttribute("json", info);
                }else{
                    request.setAttribute("json", u);
                }
            } catch (DeleteForceException ex) {
                Logger.getLogger(RestUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (us.delUser(u) == false) {
                response.setStatus(503);
                request.setAttribute("json", u);
            }else{
                request.setAttribute("json", u);
            }
        }
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
