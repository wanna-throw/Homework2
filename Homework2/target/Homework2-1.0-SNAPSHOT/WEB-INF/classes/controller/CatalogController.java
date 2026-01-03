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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

@Path("/catalog")
public class CatalogController {

    @Inject
    private CatalogService catalogService;

    @Inject
    private Models models;
    
    @Inject
    private HttpServletRequest request;

    @GET
    public String showCatalog(@QueryParam("capability") String capability,
                            @QueryParam("provider") String provider) {

        // Aquí crides al servei (podries passar els filtres al servei)
        // Per simplicitat, portem tots i filtrem a la vista o implementa el filtratge al servei
        models.put("modelsList", catalogService.findAllModels());
        return "catalog.jsp";
    }

    @GET
    @Path("/{id}")
    public String showDetail(@PathParam("id") Long id) {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String password = (session != null) ? (String) session.getAttribute("password") : null;

        ModelDTO model = catalogService.findModelById(id, username, password);

        if (model == null) {
            // Si retorna null, probablement és privat i no estem loguejats o no tenim permís
            return "redirect:/mvc/login"; // Redirigir al login
        }

        models.put("selectedModel", model);
        return "detail.jsp"; // Necessitaràs crear aquesta JSP
    }
}