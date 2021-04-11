package ph.apper.usermanagement.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String accountId;
    private boolean isVerified = false;
    private boolean isAuth = false;
    private float balance;


    private LocalDateTime dateRegistered;
    private LocalDateTime dateVerified;
    private LocalDateTime lastLogin;

    public User(String id) {
        this.id = id;
    }

    public User() {
    }


}
