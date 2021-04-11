package ph.apper.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TransferRequest {
    @JsonProperty(value = "fromAccountId")
    @NotBlank(message = "Sender ID is required")
    private String sender;

    @JsonProperty(value = "toAccountId")
    @NotBlank(message = "Receiver ID is required")
    private String receiver;

    @NotNull(message = "Amount to be transferred is required")
    private Double amount;
}