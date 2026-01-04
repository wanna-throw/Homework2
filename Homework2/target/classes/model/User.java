package deim.urv.cat.homework2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "users") // Nombre de la tabla en la BD
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Necesario para el Login

    @Column(nullable = false)
    private String password; // Necesario para el Login

    private String firstName;
    private String lastName;
    
    @Column(unique = true)
    private String email;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return fixNull(this.firstName); }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return fixNull(this.lastName); }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return fixNull(this.email); }
    public void setEmail(String email) { this.email = email; }

    private String fixNull(String in) { return (in == null) ? "" : in; }
}