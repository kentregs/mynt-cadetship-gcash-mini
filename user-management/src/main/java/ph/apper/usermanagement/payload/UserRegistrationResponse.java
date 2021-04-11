package ph.apper.usermanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegistrationResponse {

    public UserRegistrationResponse(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @JsonProperty("verification_code")
    private String verificationCode;
}
