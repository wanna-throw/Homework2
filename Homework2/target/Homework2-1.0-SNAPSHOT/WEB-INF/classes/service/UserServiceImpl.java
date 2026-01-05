package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.controller.UserForm;
import deim.urv.cat.homework2.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    // Apuntamos a la API de Homework 1
    private static final String API_URL = "http://localhost:8080/Homework1/rest/api/v1/customer";
    private final Client client;

    public UserServiceImpl() {
        this.client = ClientBuilder.newClient();
    }

    @Override
    public User findUserByUsername(String username) {
        // Obtenemos la lista de usuarios de HW1 y filtramos (ya que HW1 no tiene búsqueda por user)
        // Nota: Esto asume que el usuario 'sob' tiene permisos para listar clientes o que el endpoint es público
        try {
            List<User> users = client.target(API_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {});
            
            return users.stream()
                    .filter(u -> u.getUsername() != null && u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            List<User> users = client.target(API_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<User>>() {});
            
            return users.stream()
                    .filter(u -> u.getEmail() != null && u.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addUser(UserForm userForm) {
        // Enviamos un POST a Homework 1 para registrar el usuario allí
        WebTarget target = client.target(API_URL);
        
        // Convertimos el formulario al objeto que espera HW1
        // Asegúrate de que UserForm tenga los campos que espera HW1 (username, password, etc.)
        User userToSend = new User();
        userToSend.setUsername(userForm.getUsername());
        userToSend.setPassword(userForm.getPassword());
        userToSend.setEmail(userForm.getEmail());

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(userToSend, MediaType.APPLICATION_JSON));
        
        return response.getStatus() == 201 || response.getStatus() == 200;
    }
}