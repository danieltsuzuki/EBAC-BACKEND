package danieltsuzuki.com.github.springsecurity.dto.user;

import danieltsuzuki.com.github.springsecurity.entities.Role;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserUpdateRequest {
    @Length(max = 100, min = 3)
    private String firstName;
    @Length(max = 100)
    private String lastName;

    @Length(min = 8, max = 100)
    private String password;
    @Email
    @Length(min = 5, max = 100)
    private String email;

    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
