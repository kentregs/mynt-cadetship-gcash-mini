package ph.apper.service;
import ph.apper.exception.*;
import ph.apper.payload.*;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    void purchase(PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException;
    public ProductData getProduct(String productId) throws ProductNotFoundException;
}
