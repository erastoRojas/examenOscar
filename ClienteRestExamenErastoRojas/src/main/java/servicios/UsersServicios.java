/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
import java.io.IOException;
import java.util.List;
import model.User;

/**
 *
 * @author DAW
 */
public class UsersServicios {

    public List<User> getAllUsers() throws IOException {
        UsersDAO dao = new UsersDAO();
        return dao.getAllUsersDAO();
    }

    public User anadirUser(User usuario) {
        UsersDAO dao = new UsersDAO();
        return dao.anadirUserDAO(usuario);
    }

    public User actualizarUser(User usuario) {
        UsersDAO dao = new UsersDAO();
        return dao.actualizarUserDAO(usuario);
    }

    public String borrarUser(User usuario) {
        UsersDAO dao = new UsersDAO();
        return dao.borrarUserDAO(usuario);
    }

    public String borrarUser2(User usuario) {
        UsersDAO dao = new UsersDAO();
        return dao.borrarUser2DAO(usuario);
    }
    
}
