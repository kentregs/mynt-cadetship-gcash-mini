package domain;

import lombok.Data;

@Data
public class Activity {
    private String email;
    private String action;
    private String identifier;

    public Activity(String accId) {
        this.email = email;
    }
}
