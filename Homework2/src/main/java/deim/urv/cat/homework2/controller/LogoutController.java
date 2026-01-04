package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/logout") // URL será: /mvc/logout
@Controller
public class LogoutController {
    
    @Inject 
    Logger log;
   
    @Context
    private HttpServletRequest request;
    
    @GET
    public String logout() {
        // Obtener la sesión actual, false para no crear una si no existe
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // (Opcional) Loguear quién se desconecta para depuración
            String user = (String) session.getAttribute("username");
            log.log(Level.INFO, "Cerrando sesión para el usuario: {0}", user);
            
            // Destruir la sesión y borrar todos los datos (auth, usuario, carrito, etc.)
            session.invalidate();
        }
        
        // Redirigir al catálogo. Al no haber sesión, el controlador del catálogo
        // cargará solo los modelos públicos.
        return "redirect:/catalog"; 
    }    
}