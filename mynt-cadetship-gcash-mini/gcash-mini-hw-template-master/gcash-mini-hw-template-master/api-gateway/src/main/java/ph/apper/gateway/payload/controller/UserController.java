package ph.apper.gateway.payload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ph.apper.gateway.payload.gatewayApp;
import ph.apper.gateway.payload.activityPayload.Activity;
import ph.apper.gateway.payload.userPayload.AccountCreationRequest;

@RestController
@RequestMapping("account")
public class UserController {

    private final RestTemplate restTemplate;
    private final gatewayApp.GCashMiniProperties gCashMiniProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(RestTemplate restTemplate, gatewayApp.GCashMiniProperties gCashMiniProperties) {
        this.restTemplate = restTemplate;
        this.gCashMiniProperties = gCashMiniProperties;
    }

    @PostMapping
    public ResponseEntity create (@RequestBody AccountCreationRequest request) {
        LOGGER.info("{}", request);

        ResponseEntity<Object> response = restTemplate.postForEntity(gCashMiniProperties.getAccountUrl(), request, Object.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("Account creation successful!");

            // init activity object if operation is successful
            Activity activity = new Activity();
            activity.setAction("ACCOUNT CREATION");
            activity.setIdentifier("email = " + request.getEmail());

            restTemplate.postForEntity(gCashMiniProperties.getActivityUrl(), activity, Object.class);
        }
        else {
            LOGGER.info("Err = " + response.getStatusCode());
        }

        return ResponseEntity.ok(response.getBody());
    }
}
