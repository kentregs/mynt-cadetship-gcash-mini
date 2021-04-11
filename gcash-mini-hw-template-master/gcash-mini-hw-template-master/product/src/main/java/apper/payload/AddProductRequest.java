package apper.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddProductRequest {
    @NotBlank(message = "first_name is required")
    private String name;

    @NotBlank(message = "price is required")
    private float price;

}