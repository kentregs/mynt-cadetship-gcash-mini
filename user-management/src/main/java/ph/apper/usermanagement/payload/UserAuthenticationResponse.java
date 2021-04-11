package ph.apper.usermanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserAuthenticationResponse {
    public UserAuthenticationResponse(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_id")
    private String accountId;
}
