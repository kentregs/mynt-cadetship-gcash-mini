package ph.apper.gateway.payload.activityPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ActivityRequest {
    @JsonProperty(value = "action")
    @NotBlank(message = "action is required")
    private String action;

    @JsonProperty(value = "identifier")
    @NotBlank(message = "identifier is required")
    private String identifier;

    @JsonProperty(value = "details")
    @NotBlank(message = "details are required")
    private String details;
}