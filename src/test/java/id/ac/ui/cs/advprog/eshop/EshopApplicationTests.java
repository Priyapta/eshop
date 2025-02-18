package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EshopApplicationTest {

    @Test
    void contextLoads(ApplicationContext context) {
        // Memastikan bahwa konteks Spring Boot berhasil dimuat
        assertNotNull(context, "Konteks Spring Boot harus berhasil dimuat.");
    }

    @Test
    void mainMethodRunsSuccessfully() {
        // Memastikan bahwa metode main dapat dijalankan tanpa exception
        EshopApplication.main(new String[]{""});
    }
}