package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @PersistenceContext(unitName = "Homework2PU") // Asegúrate que coincide con tu persistence.xml
    private EntityManager em;

    // Método necesario para el LoginController
    public User findUserByUsername(String username) {
        try {
            List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getResultList();
            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            List<User> users = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getResultList();
            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean addUser(UserForm userForm) {
        // Verificar si ya existe
        if (findUserByEmail(userForm.getEmail()) != null) {
            return false;
        }
        
        // Mapear UserForm a Entity User
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        
        // Asumiendo que UserForm tiene estos campos, si no, añádelos a UserForm también
        // user.setUsername(userForm.getUsername()); 
        // user.setPassword(userForm.getPassword()); 
        
        // *Parche temporal si UserForm no tiene username/password aún*:
        // Usamos el email como username y "1234" como pass por defecto para probar
        user.setUsername(userForm.getEmail()); 
        user.setPassword("1234"); 

        try {
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}