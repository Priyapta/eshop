package id.ac.ui.cs.advprog.eshop.model;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter

public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public Product(){
        this.productId = UUID.randomUUID().toString();
    }

    public String getProductId(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public int getProductQuantity(){
        return  productQuantity;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setProductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
}
