/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CajaDAO;
import dao.DeleteForceException;
import dao.PermisosDAO;
import dao.UserDAO;
import java.util.Collection;
import java.util.List;
import model.Caja;
import model.Cosa;
import model.User;

/**
 *
 * @author DAW
 */
public class UserServicios {

    public Collection <User> getAllUsers() {
       UserDAO dao = new UserDAO();
       return dao.getAllUser(); 
    }

    public boolean updateUser(User u) {
       UserDAO dao = new UserDAO();
       return dao.updateUser(u); 
    }
    
    public boolean addUser(User u) {
       UserDAO dao = new UserDAO();
       return dao.addUser(u); 
    }

    public boolean delUser2(User u) throws DeleteForceException{
        UserDAO dao = new UserDAO();
        return dao.delUser(u); 
    }

    public boolean delUser(User u) {
        UserDAO dao = new UserDAO();
        return dao.delUserForce(u); 
    }

    public User getUser(String name) {
        UserDAO dao = new UserDAO();
        return dao.getUser(name); 
    }


    public Collection <Caja> getAllCajasUser(User u ) {
        UserDAO dao = new UserDAO();
        return dao.getAllCajasUser(u);
    }

    public boolean addCaja(User u, Caja c) {
        CajaDAO dao = new CajaDAO();
        return dao.addCaja(c);
    }

    public boolean checkPermisoCajaUser(User u, Caja c) {
        PermisosDAO dao = new PermisosDAO();
        return dao.checkPermisoCajaUser(u,c);
    }

    public boolean addCosaCaja(Caja c, Cosa co) {
        CajaDAO dao = new CajaDAO();
        return dao.addCosaACaja(co,c);
    }

    public Caja getCaja(String name) {
        CajaDAO dao = new CajaDAO();
        return dao.getCaja(name);
    }

    public boolean addCantidadCosaACaja(Cosa co, Caja c) {
        CajaDAO dao = new CajaDAO();
        return dao.addCantidadCosaACaja(co,c);
    }

    public boolean checkU(User u) {
        PermisosDAO dao = new PermisosDAO();
        return dao.checkUser(u);
    }

    public boolean addCajaUser(User u, Caja c)
    {
        UserDAO dao = new UserDAO();
        return dao.addCajaAUser(u,c);
    }
    
    
    
}
