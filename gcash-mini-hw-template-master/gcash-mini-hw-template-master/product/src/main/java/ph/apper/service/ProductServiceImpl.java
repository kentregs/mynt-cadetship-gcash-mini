package ph.apper.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ph.apper.exception.InvalidProductPurchaseException;
import ph.apper.exception.ProductNotFoundException;
import ph.apper.payload.AddProductRequest;
import ph.apper.payload.AddProductResponse;
import ph.apper.payload.ProductData;
import ph.apper.payload.PurchaseProductRequest;

@Component
@Profile({"dev", "prod"})
public class ProductServiceImpl implements ProductService{
    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        return null;
    }

    @Override
    public void purchase(PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException {

    }

    @Override
    public ProductData getProduct(String productId) throws ProductNotFoundException {
        return null;
    }
}