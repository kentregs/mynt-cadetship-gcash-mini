package apper.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Product {

    @Id
    private String productId;
    private String name;
    private float price;

    public Product(String productId) {
        this.productId = productId;
    }
}
