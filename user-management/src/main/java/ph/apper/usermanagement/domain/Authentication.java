package ph.apper.usermanagement.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Authentication {
    public Authentication(String email, String accountId) {
        this.email = email;
        this.accountId = accountId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL - Identity , Postgresql - Auto
    private Long id;

    private String email;
    private String password;
    private String accountId;

}
