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

@ApplicationScoped
public class CatalogService {
    
    // URL base de l'API de Homework1 (assegura't que el port és correcte)
    private static final String API_URL = "http://localhost:8080/Homework1/rest/api/v1/models";
    
    public List<ModelDTO> findAllModels() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL);
        
        // Pots afegir query params aquí per als filtres si cal:
        // target = target.queryParam("capability", "chat-completion");
        
        return target.request(MediaType.APPLICATION_JSON)
                     .get(new GenericType<List<ModelDTO>>() {});
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