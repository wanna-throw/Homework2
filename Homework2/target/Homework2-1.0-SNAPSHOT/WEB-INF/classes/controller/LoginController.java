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

@Controller
@Path("/login")
public class LoginController {

    @Inject
    private UserService userService; // Inyectamos el servicio JPA

    @Inject
    private HttpServletRequest request;

    @GET
    @View("login.jsp")
    public void showLoginForm() {
    }

    @POST
    public String login(@FormParam("username") String username, 
                        @FormParam("password") String password) {
        
        // 1. Buscamos el usuario en la BD Local
        // (Asegurate de añadir findUserByUsername a la interfaz UserService)
        User user = userService.findUserByUsername(username);

        // 2. Comprobamos contraseña (En texto plano para la práctica)
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("password", password); // Necesario para llamar a HW1
            session.setAttribute("user", user); // Objeto completo
            
            return "redirect:/catalog";
        } else {
             return "redirect:/login?error=true";
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