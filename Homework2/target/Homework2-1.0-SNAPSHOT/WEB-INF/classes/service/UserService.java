package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;

public interface UserService {
    
    // Método existente
    public User findUserByEmail(String email);
    
    // NUEVO: Método necesario para el LoginController
    public User findUserByUsername(String username);
    
    // Método existente
    public boolean addUser(UserForm user);
}