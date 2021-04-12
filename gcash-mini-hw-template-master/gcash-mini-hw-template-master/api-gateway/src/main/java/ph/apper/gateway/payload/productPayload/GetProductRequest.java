package ph.apper.gateway.payload.productPayload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GetProductRequest {
    @NotBlank(message = "first_name is required")
    private String name;

    @NotNull(message = "price is required")
    private float price;
}
