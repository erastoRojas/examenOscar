/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import static com.sun.jmx.mbeanserver.Util.cast;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Objeto;
import servicios.CarritoServicio;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "Carrito", urlPatterns =
{
    "carrito"
})
public class Carrito extends HttpServlet
{

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
            throws ServletException, IOException
    {
        String op = request.getParameter("op");
        String id =  request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        
        CarritoServicio cs = new CarritoServicio();
        
        Objeto ob = new Objeto();
        
        List <Objeto> listaCarrito = new ArrayList<>();
        request.getSession().setAttribute("carrito",listaCarrito);
        
        
        
        List <Objeto> listaAux = new ArrayList<>();
        
        if (op != null){  
             switch (op) {
                case "ANADIR":
                    
                    ob.setId(Integer.parseInt(id));//recogo el objeto
                    ob.setNombre(nombre);
                    ob.setTipo(tipo);
                    
                    listaAux = (List<Objeto>) request.getSession().getAttribute("carrito");
                    listaAux.add(ob);
                    
                    request.getSession().setAttribute("carrito",listaAux);
                       
                           
                break;
                
                
                case "TERMINAR":
                    break;
             }
        }
        
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
