package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.ModelDTO;
import deim.urv.cat.homework2.service.CatalogService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Path("/catalog")
public class CatalogController {

    @Inject
    private CatalogService catalogService;

    @Inject
    private Models models;
    
    @Inject
    private HttpServletRequest request;

    // 1. Carga la página vacía (esqueleto). NO hace GET a Homework1 todavía.
    @GET
    @View("catalog.jsp")
    public void showCatalog() {
        // Método vacío intencionalmente.
    }
    
    // Detalle (se mantiene igual, necesario para ver la ficha completa)
    @GET
    @Path("/{id}")
    public String showDetail(@PathParam("id") Long id) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String password = (session != null) ? (String) session.getAttribute("password") : null;

        ModelDTO model = catalogService.findModelById(id, username, password);

        if (model == null) {
            return "redirect:/login"; 
        }
        models.put("selectedModel", model);
        return "detail.jsp"; 
    }
}