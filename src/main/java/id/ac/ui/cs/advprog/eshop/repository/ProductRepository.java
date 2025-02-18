package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    public Product findById(String id) {
        Iterator<Product> iterator = productData.iterator();
        while(iterator.hasNext()){
            Product product = iterator.next();
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }


    public Product update(Product updatedProduct) {


        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(updatedProduct.getProductId())) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null; // Produk tidak ditemukan
    }
    public void deleteById(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }

}
