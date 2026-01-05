package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.ModelDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import jakarta.ws.rs.core.GenericType;

@ApplicationScoped
public class CatalogService {
    
    // URL base de l'API de Homework1 (assegura't que el port és correcte)
    private static final String API_URL = "http://localhost:8080/Homework1/rest/api/v1/models";
    
    public List<ModelDTO> findAllModels() {
        /*Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL);
        
        // Pots afegir query params aquí per als filtres si cal:
        // target = target.queryParam("capability", "chat-completion");
        
        return target.request(MediaType.APPLICATION_JSON)
                     .get(new GenericType<List<ModelDTO>>() {});*/
        Client client = ClientBuilder.newClient();
        List<ModelDTO> lista = client.target(API_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ModelDTO>>() {});
        client.close();
        System.out.println("Enviando lista");
        /*for (ModelDTO model : lista) {
            System.out.println("Model ID: " + model.getId());
            System.out.println("Name: " + model.getName());
            System.out.println("Provider: " + model.getProvider());
            System.out.println("Logo URL: " + model.getLogoUrl());
            System.out.println("Description: " + model.getDescription());
            System.out.println("Version: " + model.getVersion());
            System.out.println("Release Date: " + model.getReleaseDate());
            System.out.println("Is Private: " + model.isIsPrivate());
            System.out.println("---");
        }*/
        return lista;
    }

    public ModelDTO findModelById(Long id, String username, String password) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL).path(String.valueOf(id));
        
        Response response;
        
        // Si tenim credencials, les afegim a la capçalera per a models privats
        if (username != null && password != null && !username.isEmpty()) {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            response = target.request(MediaType.APPLICATION_JSON)
                             .header("Authorization", authHeader)
                             .get();
        } else {
            response = target.request(MediaType.APPLICATION_JSON).get();
        }

        if (response.getStatus() == 200) {
            return response.readEntity(ModelDTO.class);
        } else {
            return null; // O llençar una excepció si és 403/401
        }
    }
}