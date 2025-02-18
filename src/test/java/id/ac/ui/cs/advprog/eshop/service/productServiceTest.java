package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class productServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;  // Pastikan ini bukan interface

    private List<Product> mockDatabase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct, "Product yang dibuat harus sama dengan product yang diinput.");
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product findProduct = productService.findById(product.getProductId());
        assertEquals(product,findProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }
    @Test
    void testNotFindProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findById(product.getProductId())).thenReturn(null);
        Product findProduct = productService.findById(product.getProductId());
        assertEquals(null,findProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);


        productRepository.create(product);

// Simulasikan findById mengembalikan produk yang sama
        when(productRepository.findById(product.getProductId())).thenReturn(product);

// Simulasikan update mengembalikan produk yang diperbarui
        when(productRepository.update(product)).thenReturn(product);

// Act
        Product updatedProduct = productService.update(product);

// Assert
        assertEquals(product, updatedProduct);


    }
    @Test
    void testFindAllProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        // Act
        productService.create(product); // Menyimpan produk dalam mock repository
        List<Product> productIterator = productService.findAll(); // Mengambil semua produk

        // Assert
        assertEquals(1, productIterator.size(), "Jumlah produk yang dikembalikan harus 1");
        assertEquals(product, productIterator.get(0), "Produk pertama harus sesuai dengan yang dibuat");
    }
}
