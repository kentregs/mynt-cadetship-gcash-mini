package ph.apper.gateway.payload.productPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @JsonProperty(value = "balance")
    private Double balance;

    @JsonProperty(value = "accId")
    private String accId;
}
