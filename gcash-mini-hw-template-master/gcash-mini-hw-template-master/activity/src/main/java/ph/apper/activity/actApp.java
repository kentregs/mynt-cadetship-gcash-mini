package ph.apper.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class actApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(actApp.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
}
