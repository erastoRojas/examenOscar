/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Caja;
import model.MensajeHttp;
import model.User;
import servicios.UserServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "RestCajas", urlPatterns = {"/rest/RestCajas"})
public class RestCajas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

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
        
        User u = (User) request.getAttribute("user");
          
        if(us.getUser(u.getName()) != null){//true si existe
            
            Collection <Caja> caja = us.getAllCajasUser(u);//servicios

            if(caja == null){

                response.setStatus(204);
                MensajeHttp info = new MensajeHttp("El usuario no tiene cajas");
                request.setAttribute("json", info);

            }else{
                request.setAttribute("json", caja);
            } 
            
        } else {
            response.setStatus(204);
            MensajeHttp info = new MensajeHttp("El usuario no existe");
            request.setAttribute("json", info);
        }  
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User u = (User) request.getAttribute("user");
        Caja c = (Caja) request.getAttribute("caja");
        
        boolean añadir;
        
        añadir = us.addCaja(u,c);//crea la caja
        
        if(añadir){
            añadir = us.addCajaUser(u,c);//la asigna al user
        }    
            
        if(añadir){
            request.setAttribute("json", c);
        }else{
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("Ha ocurrido un error");

            request.setAttribute("json", info);
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
