package apper.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddProductResponse {
    private String productId;

    public AddProductResponse(String productId) {
        this.productId = productId;
    }
}
