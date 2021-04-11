package ph.apper.gateway.payload.userPayload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    public AuthenticationResponse(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("accountId")
    private String accountId;
}