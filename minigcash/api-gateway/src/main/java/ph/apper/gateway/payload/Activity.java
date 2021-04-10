package ph.apper.gateway.payload;

import lombok.Data;

@Data
public class Activity {
    private String action;
    private String identifier;
    private String details;
}
