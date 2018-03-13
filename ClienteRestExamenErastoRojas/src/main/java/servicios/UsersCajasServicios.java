/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CajasDAO;
import java.io.IOException;
import java.util.List;
import model.Caja;
import model.Cosa;
import model.User;

/**
 *
 * @author DAW
 */
public class UsersCajasServicios {

    public List <Caja> getAllCajas(User usuario) throws IOException {
        CajasDAO dao = new CajasDAO();
        return dao.getAllCajas(usuario);
    }

    public Caja anadirCaja(User usuario, Caja ca) {
        CajasDAO dao = new CajasDAO();
        return dao.anadirCajaDAO(usuario, ca);
    }

    public Caja anadirCosasCaja(User usuario, Cosa cosa, Caja caja) {
        CajasDAO dao = new CajasDAO();
        return dao.anadirCosaCajaDAO(usuario, cosa, caja);
    }

    public List <Cosa> getAllCosas(User usuario, Caja caja) {
        CajasDAO dao = new CajasDAO();
        return dao.getAllCosas(usuario,caja);
    }

    public Caja anadirCantidadCosas(User usuario, Cosa cosa, Caja caja, int cant) {
        CajasDAO dao = new CajasDAO();
        return dao.anadirCantidadCosaDAO(usuario, cosa, caja, cant);
    }
    
}
