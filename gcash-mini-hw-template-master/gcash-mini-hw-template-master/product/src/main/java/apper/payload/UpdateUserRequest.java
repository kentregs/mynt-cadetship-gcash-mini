package apper.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @JsonProperty(value = "balance")
    private Double balance;

    @JsonProperty(value = "accId")
    private String accId;
}
