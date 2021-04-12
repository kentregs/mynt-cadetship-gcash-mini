package apper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import apper.domain.*;
import apper.exception.*;
import apper.payload.*;


import java.util.ArrayList;
import java.util.List;

import static apper.util.IdService.generateProductId;


@Service
@Profile({"dev", "prod"})
public class ProductServiceImpl implements ProductService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final List<Product> products = new ArrayList<>();
    private final List<ProductPurchase> product_purchases = new ArrayList<>();


    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        //name and price
        // generate product ID
        String productId = generateProductId(7);
        Product product = new Product(productId);

        product.setProductId(productId);
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        // // Store the Product in list
        products.add(product);

        LOGGER.info("Product {} added", request.getName());

        return new AddProductResponse(productId);
    }

    @Override
    public void purchase(PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException {
        // request -> product id, account id

        // sa api gateway to get account,
        // Get product id, get account id

        // *** product id must be in the product id list
        // *** account id must be in the user-management account id list
        // Get account id balance
        // Subtract product price from acc balance
        // difference should be not negative

        // response -> successful / unsuccessful
        // get account



    }

    @Override
    public ProductData getProduct(String productId) throws ProductNotFoundException {
        Product product = getProductById(productId);
        return ProductServiceUtil.toProductData(product);
    }


    private Product getProductById(String productId) throws ProductNotFoundException {
        return products.stream()
                .filter(product -> productId.equals(product.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID" + productId + " not found"));
    }








}
