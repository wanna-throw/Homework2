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
    private String capabilities; 
    private String licenseType;
    private int contextWindow;

    // --- GETTERS Y SETTERS QUE FALTABAN ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    // ESTE ES EL QUE CAUSABA EL ERROR EN CatalogController
    public String getMainCapability() { return mainCapability; }
    public void setMainCapability(String mainCapability) { this.mainCapability = mainCapability; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public boolean isIsPrivate() { return isPrivate; }
    public void setIsPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCapabilities() { return capabilities; }
    public void setCapabilities(String capabilities) { this.capabilities = capabilities; }

    public String getLicenseType() { return licenseType; }
    public void setLicenseType(String licenseType) { this.licenseType = licenseType; }

    public int getContextWindow() { return contextWindow; }
    public void setContextWindow(int contextWindow) { this.contextWindow = contextWindow; }
}