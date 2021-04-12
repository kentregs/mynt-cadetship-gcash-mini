package apper.domain;

import lombok.Data;



@Data
public class Product {

    private String productId;
    private String name;
    private float price;

    public Product(String productId) {
        this.productId = productId;
    }
}
