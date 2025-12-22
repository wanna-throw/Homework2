package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.UserService; // O un servei d'auth específic
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.mvc.binding.MvcBinding;
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
    private HttpServletRequest request;

    @GET
    @View("login.jsp") // Crea una login.jsp simple
    public void showLoginForm() {
    }

    @POST
    public String login(@FormParam("username") String username, 
                        @FormParam("password") String password) {
        
        // VALIDACIÓ: Intenta fer una crida a Homework1 a un endpoint protegit
        // (per exemple, GET /customer/me o similar) usant aquestes credencials via CatalogService o AuthService.
        // Si retorna 200 OK, les credencials són bones.
        
        // SIMULACIÓ (perquè funcioni ràpid segons l'enunciat "sob"/"sob"):
        // En un entorn real, has de verificar contra l'API REST.
        if ("sob".equals(username) && "sob".equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("password", password); // Guardem per fer les peticions REST
            return "redirect:/catalog";
        } else {
             // Si falla
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