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
import jakarta.ws.rs.core.MediaType;     
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

    // Renderiza la vista inicial JSP
    @GET
    @View("catalog.jsp") 
    public void showCatalog() {
        // Ya no cargamos la lista aquí, la cargará el JS mediante el endpoint de abajo
        // Solo pasamos el usuario si existe para la UI
    }

    // NUEVO ENDPOINT: Devuelve JSON para el Scroll Infinito
    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModelDTO> getCatalogData(@QueryParam("page") int page, 
                                         @QueryParam("provider") String provider,
                                         @QueryParam("capability") String capability) {
        
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String password = (session != null) ? (String) session.getAttribute("password") : null;

        // 1. Obtener todos los modelos (pasando credenciales para ver los privados)
        List<ModelDTO> allModels = catalogService.findAllModels(); 
        
        // NOTA: Si CatalogService.findAllModels() no acepta credenciales, debes actualizarlo 
        // o filtrar aquí los privados si el usuario es null.
        // Asumiremos que findAllModels devuelve TO-DO y filtramos visibilidad:
        if (username == null) {
            allModels = allModels.stream()
                    .filter(m -> !m.isIsPrivate()) // Solo públicos si no hay login
                    .collect(Collectors.toList());
        }

        // 2. Filtrado por Provider/Capability
        if (provider != null && !provider.isEmpty()) {
            allModels = allModels.stream()
                .filter(m -> provider.equalsIgnoreCase(m.getProvider()))
                .collect(Collectors.toList());
        }
        if (capability != null && !capability.isEmpty()) {
             // Asumiendo que capability es un string, si es lista usa contains
            allModels = allModels.stream()
                .filter(m -> m.getMainCapability() != null && m.getMainCapability().contains(capability))
                .collect(Collectors.toList());
        }

        // 3. Paginación Manual (6 items por página)
        int pageSize = 6;
        int fromIndex = (page - 1) * pageSize;
        
        if (fromIndex >= allModels.size()) {
            return Collections.emptyList();
        }
        
        int toIndex = Math.min(fromIndex + pageSize, allModels.size());
        return allModels.subList(fromIndex, toIndex);
    }

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