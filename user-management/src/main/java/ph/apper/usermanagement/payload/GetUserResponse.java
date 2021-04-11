package ph.apper.usermanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetUserResponse {


    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    private String email;

    @JsonProperty(value = "balance")
    private float balance;
}
