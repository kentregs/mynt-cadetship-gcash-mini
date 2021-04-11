package ph.apper.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;
}