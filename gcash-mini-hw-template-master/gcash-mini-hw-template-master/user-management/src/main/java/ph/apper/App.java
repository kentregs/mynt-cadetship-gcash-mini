package ph.apper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ph.apper.controller.UserController;
import ph.apper.service.UserService;

@SpringBootApplication()
@ComponentScan(basePackageClasses = UserController.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}