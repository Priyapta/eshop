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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
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
        // Arrange (Membuat produk contoh)
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("fb558e9f-1c39-460e-8860-71af6af63bd7");
        product2.setProductName("Sabun Cap Melati");
        product2.setProductQuantity(50);

        // Simulasi perilaku iterator untuk mock repository
        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(product1);
        mockProductList.add(product2);

        Iterator<Product> mockIterator = mockProductList.iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        // Act
        List<Product> result = productService.findAll();

        // Assert
        assertEquals(2, result.size(), "Jumlah produk harus 2");
        assertEquals(product1, result.get(0), "Produk pertama harus sesuai");
        assertEquals(product2, result.get(1), "Produk kedua harus sesuai");

        verify(productRepository, times(1)).findAll(); // Pastikan findAll() dipanggil sekali
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);


        productRepository.create(product);
        when(productRepository.findById(product.getProductId())).thenReturn(product);
        productService.delete(product.getProductId());
        verify(productRepository, times(1)).findById(product.getProductId());



    }
    @Test
    void testDeleteProductNotFound() {
        // Arrange
        String productId = "456";

        when(productRepository.findById(productId)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.delete(productId);
        });

        String expectedMessage = "Product with ID " + productId + " not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).deleteById(productId);
    }
}