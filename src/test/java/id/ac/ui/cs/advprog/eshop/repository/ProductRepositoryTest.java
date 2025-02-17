package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    private ProductRepository productRepository;
    private Product sampleProduct;


    @BeforeEach
    void setUp() {

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());


    }
    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneproduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testUpdateProduct() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Laptop");
        sampleProduct.setProductQuantity(10);
        productRepository.create(sampleProduct);
        sampleProduct.setProductName("Gaming Laptop");
        sampleProduct.setProductQuantity(15);

        Product updatedProduct = productRepository.update(sampleProduct);
        assertNotNull(updatedProduct);
        assertEquals("Gaming Laptop", updatedProduct.getProductName());
        assertEquals(15, updatedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId(UUID.randomUUID().toString());
        nonExistentProduct.setProductName("Tablet");
        nonExistentProduct.setProductQuantity(7);

        Product updatedProduct = productRepository.update(nonExistentProduct);
        assertNull(updatedProduct);
    }
    @Test
    void testUpdateNotValidProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);


        Product updateProduct = new Product();
        updateProduct.setProductId("999");
        updateProduct.setProductName("Sampo Cap Bambang");
        updateProduct.setProductQuantity(100);

        productRepository.create(product);

        Product updatedProduct = productRepository.update(updateProduct);
        assertNull(updatedProduct);

    }

    @Test
    void testDeleteProduct() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Laptop");
        sampleProduct.setProductQuantity(10);
        productRepository.create(sampleProduct);
        String idToDelete = sampleProduct.getProductId();
        productRepository.deleteById(idToDelete);
        assertNull(productRepository.findById(idToDelete));
    }
    @Test
    void testDeleteProductNotFound() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Laptop");
        sampleProduct.setProductQuantity(10);
        productRepository.create(sampleProduct);
        String fakeId = UUID.randomUUID().toString();
        productRepository.deleteById(fakeId);
        assertNotNull(productRepository.findById(sampleProduct.getProductId()));
    }

    @Test
    void testFindByIdNonExisting() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("999");
        assertNull(foundProduct);
    }

    @Test
    void testFindByIdExisting() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);
        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Sampo Cap Bambang", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

}
