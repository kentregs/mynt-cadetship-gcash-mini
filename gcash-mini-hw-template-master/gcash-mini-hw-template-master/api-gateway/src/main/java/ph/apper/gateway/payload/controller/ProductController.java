package ph.apper.gateway.payload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ph.apper.gateway.payload.activityPayload.Activity;
import ph.apper.gateway.payload.gatewayApp;
import ph.apper.gateway.payload.productPayload.AddProductRequest;
import ph.apper.gateway.payload.productPayload.AddProductResponse;
import ph.apper.gateway.payload.productPayload.PurchaseProductRequest;

@RestController
@RequestMapping("product")
public class ProductController {

    private final RestTemplate restTemplate;
    private final gatewayApp.GCashMiniProperties gCashMiniProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public ProductController(RestTemplate restTemplate, gatewayApp.GCashMiniProperties gCashMiniProperties) {
        this.restTemplate = restTemplate;
        this.gCashMiniProperties = gCashMiniProperties;
    }

    @PostMapping
    public ResponseEntity addProduct (@RequestBody AddProductRequest request) {
        LOGGER.info("{}", request);

        ResponseEntity<Object> response
                = restTemplate.postForEntity(gCashMiniProperties.getProductUrl(), request, Object.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("Add product successful!");

            Activity activity = new Activity();
            activity.setAction("ADD PRODUCT");
            activity.setIdentifier(request.getName());

            restTemplate.postForEntity(gCashMiniProperties.getActivityUrl(), activity, Object.class);
        }
        else {
            LOGGER.info("Err = " + response.getStatusCode());
        }

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("{id}")
    public ResponseEntity getProduct (@PathVariable("id") String productId) {
        ResponseEntity<Object> response
                = restTemplate.getForEntity(gCashMiniProperties.getProductUrl() + "/" +productId, Object.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Get product successful
            return ResponseEntity.ok(response.getBody());
        }

        return ResponseEntity.status(response.getStatusCode()).build();
    }

    @PostMapping("purchase")
    public ResponseEntity purchase(@RequestBody PurchaseProductRequest request) {
        LOGGER.info("{}", request);

        ResponseEntity<Object> response
                = restTemplate.postForEntity(gCashMiniProperties.getProductUrl()+"/purchase", request, Object.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("Product purchase successful!");
            Activity activity = new Activity();
            activity.setAction("PURCHASE PRODUCT");
            activity.setIdentifier(request.getAccountId());

            restTemplate.postForEntity(gCashMiniProperties.getActivityUrl(), activity, Object.class);
        }
        else {
            LOGGER.info("Err = " + response.getStatusCode());
        }

        return ResponseEntity.ok(response.getBody());
    }
}