package ph.apper.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetProductRequest {
    @NotBlank(message = "first_name is required")
    private String name;

    @NotBlank(message = "price is required")
    private float price;
}