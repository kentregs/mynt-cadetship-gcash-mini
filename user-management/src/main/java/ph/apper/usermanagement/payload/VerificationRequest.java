package ph.apper.usermanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerificationRequest {

    @NotBlank(message = "email is required")
    private String email;

    @JsonProperty("verificationCode")
    @NotBlank(message = "verification_code is required")
    private String verificationCode;
}

