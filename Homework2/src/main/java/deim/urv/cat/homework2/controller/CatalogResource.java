package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.ModelDTO;
import deim.urv.cat.homework2.service.CatalogService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/catalog")
public class CatalogResource {

    @Inject
    private CatalogService catalogService;

    @Inject
    private HttpServletRequest request;

    // Endpoint JSON que llama el JavaScript.
    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogData() {
        try {
            HttpSession session = request.getSession(false);
            String username = (session != null) ? (String) session.getAttribute("username") : null;
            String password = (session != null) ? (String) session.getAttribute("password") : null;

            // Recuperamos modelos.
            List<ModelDTO> allModels = catalogService.findAllModels();
            for (ModelDTO model : allModels) {
                System.out.println("Model ID: " + model.getId());
                System.out.println("Name: " + model.getName());
                System.out.println("Provider: " + model.getProvider());
                System.out.println("Logo URL: " + model.getLogoUrl());
                System.out.println("Description: " + model.getDescription());
                System.out.println("Version: " + model.getVersion());
                System.out.println("Release Date: " + model.getReleaseDate());
                System.out.println("Is Private: " + model.isIsPrivate());
                System.out.println("---");
            }
            return Response.ok(allModels).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Internal server error: " + e.getMessage()).build();
        }
    }
}