package com.betaarrays.androidassignments.response;



import java.util.List;

public class ResponseClass {
    private List<ProductResponse> products;

    // Constructor, getters, and setters

    public ResponseClass(List<ProductResponse> products) {
        this.products = products;
    }

    // Getter and setter for products

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
