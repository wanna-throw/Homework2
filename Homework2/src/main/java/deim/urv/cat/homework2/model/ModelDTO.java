package deim.urv.cat.homework2.model;

import jakarta.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
// import java.util.Date; // Quitamos Date para evitar errores 500

public class ModelDTO implements Serializable {
    
    private Long id;
    private String name;
    private String provider;
    private String logoUrl;
    
    // CAMBIO CLAVE 1: Mapeamos "summary" (del JSON) a esta variable "description".
    // Así tu tarjeta mostrará el resumen corto de 30 palabras, no la descripción larga.
    @JsonbProperty("summary") 
    private String description; 
    
    private String version;

    // CAMBIO CLAVE 2: Usar String para la fecha evita errores de deserialización (Error 500)
    private String releaseDate;

    @JsonbProperty("private") 
    private boolean isPrivate; 

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    // Getter y Setter adaptados a String
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public boolean isIsPrivate() { return isPrivate; }
    public void setIsPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
}