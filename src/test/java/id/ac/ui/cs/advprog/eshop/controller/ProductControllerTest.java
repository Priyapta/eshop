package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);



        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    public void testProductList() throws Exception {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setProductQuantity(10);

        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("products", productList));

        verify(productService, times(1)).findAll();
    }

    @Test
    public void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productService.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", product));

        verify(productService, times(1)).findById("123");
    }

    @Test
    public void testEditProductPageNotFound() throws Exception {
        when(productService.findById("999")).thenReturn(null);

        mockMvc.perform(get("/product/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).findById("999");
    }

    @Test
    void testEditProduct_WhenExceptionThrown_ShouldRedirectToList() throws Exception {
        // Mock agar service.findById(id) melemparkan Exception
        String productId = "invalid-id";
        when(productService.findById(productId)).thenThrow(new RuntimeException("Database Error"));

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().is3xxRedirection()) // Memastikan terjadi redirect
                .andExpect(redirectedUrl("/product/list")); // Redirect ke halaman list

        // Verifikasi bahwa service.findById dipanggil dengan parameter yang benar
        verify(productService, times(1)).findById(productId);
    }


    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Updated Product");
        product.setProductQuantity(20);

        // Mock the update method to return the updated product
        when(productService.update(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/edit/123")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).delete("123");

        mockMvc.perform(post("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete("123");
    }
}