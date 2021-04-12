package ph.apper.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {
    private Double balance;
    private String accId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private boolean isVerified = false;
    private boolean isAuthenticated = false;

    private LocalDateTime lastLogin;
    private LocalDateTime dateVerified;
    private LocalDateTime dateRegistered;
    private LocalDateTime dateAuthenticated;
}