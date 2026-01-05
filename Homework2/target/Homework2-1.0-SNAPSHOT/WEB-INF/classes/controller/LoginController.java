package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.UserService;
import deim.urv.cat.homework2.model.User;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import java.util.Base64;

@Controller
@Path("/login")
public class LoginController {

    @Inject
    private UserService userService; 

    @Inject
    private HttpServletRequest request;

    @GET
    @View("login.jsp")
    public void showLoginForm() {
    }

    @POST
    public String login(@FormParam("username") String username, 
                        @FormParam("password") String password) {
        
        // 1. Verificación REAL contra Homework 1
        // Intentamos acceder a una ruta protegida o pública de HW1 usando las credenciales
        // Si devuelve 200, las credenciales son válidas en la BD remota.
        boolean loginSuccess = checkCredentialsWithHW1(username, password);

        if (loginSuccess) {
            // Obtenemos los datos del usuario para la sesión (opcional)
            User user = userService.findUserByUsername(username);
            
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("password", password); // Necesario para CatalogService
            session.setAttribute("user", user);
            
            return "redirect:/catalog";
        } else {
             return "redirect:/login?error=true";
        }
    }
    
    private boolean checkCredentialsWithHW1(String username, String password) {
        try {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            // Llamamos a un endpoint que sepamos que existe (ej. lista de modelos o clientes)
            Response response = ClientBuilder.newClient()
                    .target("http://localhost:8080/Homework1/rest/api/v1/models") // O /customer
                    .request()
                    .header("Authorization", authHeader)
                    .get();
            
            // Si responde 200 OK, significa que HW1 aceptó el usuario/password
            return response.getStatus() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    @GET
    @Path("/logout")
    public String logout() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/catalog";
    }
}