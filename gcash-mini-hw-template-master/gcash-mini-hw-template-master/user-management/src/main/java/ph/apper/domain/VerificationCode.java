package ph.apper.domain;

import lombok.Data;

@Data
public class VerificationCode {

    public VerificationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    private String email;
    private String code;
}