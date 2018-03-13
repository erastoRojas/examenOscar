/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Caja;
import model.Cosa;
import model.User;
import servicios.UsersCajasServicios;
import servicios.UsersServicios;

/**
 *
 * @author DAW
 */
@WebServlet(name = "UsersCajas", urlPatterns = {"/UsersCajas"})
public class UsersCajas extends HttpServlet {

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
        
        UsersCajasServicios ucs = new UsersCajasServicios();
        String op = request.getParameter("op");

        try {
            if (op != null) {
                
                String nombre = request.getParameter("nombre");
                String pass = request.getParameter("pass");
                
                String nombreCaja = request.getParameter("nombreCaja");
                String nombreCosa = request.getParameter("nombreCosa");
                String cantidadCosa = request.getParameter("cantidadCosa");
                
                
                User usuario = new User();
                usuario.setName(nombre);
                usuario.setPassword(pass);
                
                Caja caja = new Caja();
                caja.setNombre(nombreCaja);
                
                Cosa cosa = new Cosa();
                cosa.setNombre(nombreCosa);
               
                switch (op) {
                        
                    case "ListarCajas":
                        
                        List <Caja> cajas = ucs.getAllCajas(usuario);//llamada a servicios
                        
                        if(cajas != null){
                            request.setAttribute("users", cajas);
                        }else{
                            request.setAttribute("mensajeUser", "no hay cajas");
                        }

                        break;
                        
                    case "AnadirCaja":
                        
                        caja = ucs.anadirCaja(usuario,caja);//llamada a servicios
                        
                        if(caja != null){
                            request.setAttribute("mensajeUser", "bien");
                        }else{
                            request.setAttribute("mensajeUser", "mal");
                        }
                            
                        break;
                    
                    case "AnadirCosasCaja":
                        
                        caja = ucs.anadirCosasCaja(usuario,cosa,caja);//llamada a servicios
                        
                        if(caja != null){
                            request.setAttribute("mensajeUser", "bien");
                        }else{
                            request.setAttribute("mensajeUser", "mal");
                        }

                        break;
                        
                    case "ListarCosas":
                        
                        request.setAttribute("listarCosas", ucs.getAllCosas(usuario,caja));//llamada a servicios
                        
                        List <Cosa> cosas = ucs.getAllCosas(usuario,caja);///llamada a servicios
                        
                        if(cosas != null){
                            request.setAttribute("users", cosas);
                        }else{
                            request.setAttribute("mensajeUser", "no hay cosas");
                        }
                        
                        break;
                        
                    case "AnadirCantidadCosas":

                        int cant = (Integer.parseInt(cantidadCosa));
                        
                        caja = ucs.anadirCantidadCosas(usuario,cosa,caja,cant);//llamada a servicios
                        
                        if(caja != null){
                            request.setAttribute("mensajeUser", "bien");
                        }
                        else{
                            request.setAttribute("mensajeUser", "mal");
                        }

                        break;
                }

            }
                request.getRequestDispatcher("pintarCajas.jsp").forward(request, response);
            
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                response.getWriter().write("401");
                request.setAttribute("error", "Se ha superado la cuota establecida");
                
            } else if (ex.getMessage().contains("503")) {
                response.getWriter().write("503");
                
            } else {
                request.setAttribute("error", "Ha ocurrido un error");
            }

            if (op == null) {
                request.getRequestDispatcher("pintarCajas.jsp").forward(request, response);
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
