package ph.apper.gateway.payload.userPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountCreationResponse {
    public AccountCreationResponse(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @JsonProperty("verificationCode")
    private String verificationCode;
}