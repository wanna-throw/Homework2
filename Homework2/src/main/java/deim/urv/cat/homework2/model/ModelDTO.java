package deim.urv.cat.homework2.model;

import java.io.Serializable;
import java.util.Date;

public class ModelDTO implements Serializable {
    private Long id;
    private String name;
    private String provider;
    private String summary;
    private String mainCapability;
    private String logoUrl;
    private boolean isPrivate;
    
    // Detalls complets
    private String version;
    private Date releaseDate;
    private String description;
    private String capabilities; // Llista separada per comes
    private String licenseType;
    private int contextWindow;

    // Getters i Setters (Genera'ls amb l'IDE)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    // ... Afegeix la resta de getters i setters per a tots els camps
    
    public boolean isIsPrivate() { return isPrivate; }
    public void setIsPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
}