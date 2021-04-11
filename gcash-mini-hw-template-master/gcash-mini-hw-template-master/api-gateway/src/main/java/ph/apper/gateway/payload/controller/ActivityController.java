package ph.apper.gateway.payload.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ph.apper.gateway.payload.gatewayApp;
import ph.apper.gateway.payload.activityPayload.Activity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {

    private final RestTemplate restTemplate;
    private final gatewayApp.GCashMiniProperties gCashMiniProperties;

    public ActivityController(RestTemplate restTemplate, gatewayApp.GCashMiniProperties gCashMiniProperties) {
        this.restTemplate = restTemplate;
        this.gCashMiniProperties = gCashMiniProperties;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAll () {
        ResponseEntity<Activity[]> response = restTemplate.getForEntity(gCashMiniProperties.getActivityUrl(), Activity[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Activity> activities = Arrays.asList(response.getBody());
            return ResponseEntity.ok(activities);
        }

        return ResponseEntity.status(response.getStatusCode()).build();
    }

}
