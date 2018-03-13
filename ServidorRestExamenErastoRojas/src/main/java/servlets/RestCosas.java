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
import model.Caja;
import model.Cosa;
import model.MensajeHttp;
import model.User;
import servicios.UserServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "RestCosas", urlPatterns = {"/rest/RestCosas"})
public class RestCosas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    UserServicios us = new UserServicios();

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
        
        User u = (User) request.getAttribute("user");
        Caja c = (Caja) request.getAttribute("caja");
        
            c = us.getCaja(u.getName());
            
            if(c != null){
                request.setAttribute("json", c);
                
            }else{
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("No tiene caja");
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
    
    @Override//AÑADIR COSAS A LA CAJA DE UN USUARIO
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User u = (User) request.getAttribute("user");
        Caja c = (Caja) request.getAttribute("caja");
        Cosa co = (Cosa) request.getAttribute("cosa");
        String añadirCantidad = (String) request.getAttribute("cantidad");

        //if(us.checkPermisoCajaUser(u,c)){
            
            if(añadirCantidad == null){
            //primero 
                //co.setCantidad(0);
                if(us.addCosaCaja(c,co)){
                    request.setAttribute("json", c);
                }else{
                    response.setStatus(500);
                    MensajeHttp info = new MensajeHttp("No se ha podido añadir cosas a la caja");
                    request.setAttribute("json", info);
                }
            }else{
                
                int cant = Integer.parseInt(añadirCantidad);
                
                co.setCantidad(cant);
                
                if(us.addCantidadCosaACaja(co,c)){
                    
                    request.setAttribute("json", c);
                }else{
                    response.setStatus(500);
                    MensajeHttp info = new MensajeHttp("No se ha podido añadir cantidad a la caja");
                    request.setAttribute("json", info);
                }
            }//
/*
        }else{
            response.setStatus(500);
            MensajeHttp info = new MensajeHttp("No tiene permisos");
            request.setAttribute("json", info);
        } */
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
