package ph.apper.gateway.payload.productPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PurchaseProductRequest {
    @JsonProperty(value = "productId")
    private String productId;

    @JsonProperty(value = "accountId")
    private String accountId;
}
