package ph.apper.gateway;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({App.GCashMiniProperties.class})
public class App {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Data
    @ConfigurationProperties(prefix = "gcash.mini")
    public static class GCashMiniProperties {
        private String activityUrl;
    }
}
