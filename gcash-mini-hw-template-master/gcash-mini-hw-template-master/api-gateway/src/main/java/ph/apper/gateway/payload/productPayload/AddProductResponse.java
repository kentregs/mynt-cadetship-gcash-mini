package ph.apper.gateway.payload.productPayload;

import lombok.Data;

@Data
public class AddProductResponse {
    private String productId;

    public AddProductResponse(String productId) {
        this.productId = productId;
    }
}
