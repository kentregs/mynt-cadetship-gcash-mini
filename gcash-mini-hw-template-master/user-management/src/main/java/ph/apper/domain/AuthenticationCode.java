package ph.apper.domain;

public class AuthenticationCode {
    public AuthenticationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    private String email;
    private String code;
}