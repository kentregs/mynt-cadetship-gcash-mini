package ph.apper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("ph.apper")
public class userApp {
    public static void main(String[] args) {
        SpringApplication.run(userApp.class, args);
    }
}