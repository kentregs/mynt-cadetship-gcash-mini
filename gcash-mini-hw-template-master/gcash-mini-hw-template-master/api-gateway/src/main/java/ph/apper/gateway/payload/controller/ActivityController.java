package ph.apper.gateway.payload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ph.apper.gateway.payload.activityPayload.ActivityRequest;
import ph.apper.gateway.payload.gatewayApp;
import ph.apper.gateway.payload.activityPayload.Activity;
import ph.apper.gateway.payload.userPayload.GenericResponse;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {

    private final RestTemplate restTemplate;
    private final gatewayApp.GCashMiniProperties gCashMiniProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

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

    @PostMapping
    public ResponseEntity add (@RequestBody ActivityRequest request) {
        LOGGER.info("{}", request);
        ResponseEntity<Object> response = restTemplate.postForEntity(gCashMiniProperties.getActivityUrl(), request, Object.class);

        return ResponseEntity.ok(new GenericResponse("Activity added successfully!"));
    }
}