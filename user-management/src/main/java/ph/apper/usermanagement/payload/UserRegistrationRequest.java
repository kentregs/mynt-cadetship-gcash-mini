package ph.apper.usermanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {
    @JsonProperty(value = "firstName")
    @NotBlank(message = "first_name is required")
    private String firstName;

    @JsonProperty(value = "lastName")
    @NotBlank(message = "last_name is required")
    private String lastName;

    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

}
