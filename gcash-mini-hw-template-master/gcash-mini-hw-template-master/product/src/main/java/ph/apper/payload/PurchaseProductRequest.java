package ph.apper.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PurchaseProductRequest {
    @JsonProperty(value = "product_id")
    private String productId;

    @JsonProperty(value = "account_id")
    private String accountId;
}
