package ph.apper.usermanagement.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class VerificationCode {

    public VerificationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public VerificationCode() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL - Identity , Postgresql - Auto
    private Long id;

    private String email;
    private String code;
}
