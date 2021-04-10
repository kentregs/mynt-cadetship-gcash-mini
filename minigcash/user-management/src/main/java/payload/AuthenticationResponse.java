package payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    public AuthenticationResponse(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_id")
    private String accountId;
}
