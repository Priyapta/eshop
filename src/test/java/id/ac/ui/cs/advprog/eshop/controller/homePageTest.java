package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomepageControllerTest { // Nama kelas diubah ke PascalCase

    @Mock
    private ProductService service; // Tambahkan mock untuk ProductService

    @Mock
    private Model model;

    @InjectMocks
    private HomepageController homepageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inisialisasi mock object
    }

    @Test
    void testHomePage() {
        // Act
        String viewName = homepageController.createProductPage(model);

        // Assert
        assertEquals("homePage", viewName);
    }
}