package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

@Named("userForm")
@RequestScoped
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
    // JSR 303 validation
    @NotBlank
    @FormParam("username")
    @MvcBinding
    @Size(min=2, max=30, message = "Username must be between 2 and 30 characters")
    private String username;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("password")
    @MvcBinding
    @Size(min=2, max=30, message = "Password must be between 2 and 30 characters")
    private String password;

    @NotBlank
    @FormParam("email")
    @Email(message = "Email should be valid")
    @MvcBinding
    private String email;
    
    public String getUsername() {
        return fixNull(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return fixNull(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }
}
