package ph.apper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.apper.exception.*;
import ph.apper.payload.*;
import ph.apper.service.ProductService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("product")
@ComponentScan("ph.apper.service.ProductService")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<AddProductResponse> addProduct (
            @Valid @RequestBody AddProductRequest request) {
        AddProductResponse response = productService.addProduct(request);

        LOGGER.info("Product {} added successfully", request.getName());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable("id") String productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping("purchase")
    public ResponseEntity<GenericResponse> purchaseProduct(
            @Valid @RequestBody PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException {
        LOGGER.info("Product Purchase request received");
        productService.purchase(request);
        LOGGER.info("Product Purchase successful");

        return ResponseEntity.ok(new GenericResponse("Product Purchase successful"));
    }
}