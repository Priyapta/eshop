package id.ac.ui.cs.advprog.eshop.model;



import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private List<Product> products;
    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sampo Cap usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

    }
    @Test
    void testCreateOrderEmptyProducts() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                    this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderProductsDefaultProducts() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
        assertSame(this.products,order.getProducts());
        assertEquals(2,order.getProducts().size());
        assertEquals("Sampo Cap Bambang",order.getProducts().get(0).getProductName());
        assertEquals("Sampo Cap usep",order.getProducts().get(1).getProductName());

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b",order.getId());
        assertEquals(1708560000L,order.getOrderTime());
        assertEquals("Safira Sudrajat",order.getAuthor());
        assertEquals("WAITING_PAYMENT",order.getStatus());
    }
    @Test
    void testCreateOrderSuccessProducts() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat",OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(),order.getStatus());

    }

    @Test
    void testCreateOrderInvalidProducts() {
        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                    this.products, 1708560000L, "Safira Sudrajat","MEOW");
        });
    }

    @Test
    void testSetStatusToCalled(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat","SUCCESS");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED",order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat","SUCCESS");

        assertThrows(IllegalArgumentException.class, () ->{
            order.setStatus("MEOW");
        });
    }
}
