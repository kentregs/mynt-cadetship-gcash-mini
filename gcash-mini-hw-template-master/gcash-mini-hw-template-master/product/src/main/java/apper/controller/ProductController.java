package apper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import apper.service.ProductService;
import apper.payload.*;
import apper.exception.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    private final RestTemplate restTemplate;

    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }



    @PostMapping() // add product
    public ResponseEntity<AddProductResponse> addProduct(
            @Valid @RequestBody AddProductRequest request) {
        AddProductResponse response = productService.addProduct(request);

        LOGGER.info("Product {} added successfully");

        return ResponseEntity.ok(response);

    }


    @GetMapping("{id}") // get product by ID
    public ResponseEntity<ProductData> getProduct(@PathVariable("id") String productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping("purchase")
    public ResponseEntity<GenericResponse> purchaseProduct(
            @Valid @RequestBody PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException {
        // request -> account id, product id
        // get product data

        ProductData product_data = productService.getProduct(request.getProductId());
        Float product_cost = product_data.getPrice();
        // get account by get id ((call the method via rest template ))
        ResponseEntity<UserData> UserDataResponse = restTemplate.getForEntity("http://localhost:8081/account/"+request.getAccountId(), UserData.class);
        LOGGER.info(String.valueOf(UserDataResponse));
        Double user_balance = UserDataResponse.getBody().getBalance();
        LOGGER.info(String.valueOf(product_cost));
        LOGGER.info(String.valueOf(user_balance));

        if (user_balance > product_cost){

            // get new user balance
            double new_balance = user_balance - product_cost;

            // create a post payload for the post method ((copy user management))
            //post the user balance to the list in the user management microservice -> updating of balance happens in the user management microservice
            UpdateUserRequest update_user_request = new UpdateUserRequest();
            update_user_request.setBalance(new_balance);
            update_user_request.setAccId(request.getAccountId());

            restTemplate.postForEntity("http://localhost:8081/account/" + request.getAccountId(), update_user_request, GenericResponse.class);

        }
        else throw new InvalidProductPurchaseException("Not enough funds!");


        // define logic functions in product service class


        LOGGER.info("Product Purchase request received");
        productService.purchase(request);
        LOGGER.info("Product Purchase successful");

        return ResponseEntity.ok(new GenericResponse("Product Purchase successful"));

    }

}