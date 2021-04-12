package apper.service;
import apper.payload.*;
import apper.exception.*;


public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    void purchase(PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException;
    public ProductData getProduct(String productId) throws ProductNotFoundException;
}
