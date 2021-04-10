package payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountCreationResponse {
    public AccountCreationResponse(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @JsonProperty("verification_code")
    private String verificationCode;
}
