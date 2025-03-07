package id.ac.ui.cs.advprog.eshop.model;



import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> voucherDetails;
    private Map<String, String> bankDetails;
    private Order customerOrder;
    private List<Product> products;
    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        this.voucherDetails = new HashMap<>();
        voucherDetails.put("voucherCode", "ESHOP12345678XYZ");
        this.bankDetails = new HashMap<>();
        bankDetails.put("bankName", "Pemuda");
        bankDetails.put("referenceCode", "ABC123456789");

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
        this.customerOrder = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");

    }
    @Test
    void testPaymentNullData() {
        assertAll(
                () ->assertThrows(NullPointerException.class,
                        () -> new Payment("VOUCHER",null,this.customerOrder)),
                () ->assertThrows(NullPointerException.class,
                        () -> new Payment("BANK",null,this.customerOrder)),
                () ->assertThrows(NullPointerException.class,
                        () -> new Payment("VOUCHER",this.voucherDetails,null)),
                () ->assertThrows(NullPointerException.class,
                        () -> new Payment("BANK",this.voucherDetails,null))

        );


    }

    @Test
    void testVoucherPayment() {
        assertAll(
                () -> {
                Map<String,String> invalidVoucherCode = new HashMap <> ();
                invalidVoucherCode.put("voucherCode",null);
                Payment payment = new Payment("VOUCHER", invalidVoucherCode, this.customerOrder);
                assertEquals("REJECTED",payment.getStatus());



                },
                () -> {
                    Map<String, String> invalidVoucherPrefix = new HashMap<>();
                    invalidVoucherPrefix.put("voucherCode", "7777777777777777");
                    Payment payment = new Payment("VOUCHER", invalidVoucherPrefix, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());

                },
                () -> {
                    Map<String, String> invalidVoucherNumbers = new HashMap<>();
                    invalidVoucherNumbers.put("voucherCode", "ESHOP9999XYZAXWY");
                    Payment payment = new Payment("VOUCHER", invalidVoucherNumbers, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());
                    System.out.println(payment.getStatus());
                },
                () -> {
                    Map<String, String> invalidVoucherLength = new HashMap<>();
                    invalidVoucherLength.put("voucherCode", "ESHOP9999XYZ88");
                    Payment payment = new Payment("VOUCHER", invalidVoucherLength, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());
                }
        );
    }
    @Test
    void testBankPaymentValidation() {
        assertAll(
                () -> {
                    Map<String,String> invalidBank = new HashMap <> ();
                    invalidBank.put("bankName",null);
                    invalidBank.put("referenceCode","ABC123456789");
                    Payment payment = new Payment("BANK", invalidBank, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());


                },
                () -> {
                    Map<String,String> invalidBank = new HashMap <> ();
                    invalidBank.put("bankName","Mandiri");
                    invalidBank.put("referenceCode","");
                    Payment payment = new Payment("BANK", invalidBank, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());
                },
                () -> {
                    Map<String,String> invalidBank = new HashMap <> ();
                    invalidBank.put("bankName",null);
                    invalidBank.put("referenceCode",null);
                    Payment payment = new Payment("BANK", invalidBank, this.customerOrder);
                    assertEquals("REJECTED", payment.getStatus());
                }

        );

    }

    @Test
    void testPaymentMethodAndStatusValidation() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Payment("INVALID_METHOD", this.voucherDetails, this.customerOrder)),
                () -> {
                    Payment paymentVoucher = new Payment("VOUCHER", this.voucherDetails, this.customerOrder);
                    assertThrows(IllegalArgumentException.class,
                            () -> paymentVoucher.setStatus("Hello"));
                },
                () -> {
                    Payment paymentVoucher = new Payment("VOUCHER", this.voucherDetails, this.customerOrder);
                    paymentVoucher.setStatus("REJECTED");
                    assertEquals("REJECTED", paymentVoucher.getStatus());
                }
        );
    }

    @Test
    void testSuccessfulPaymentCreation() {
        assertAll(
                () -> {
                    Payment voucherPayment = new Payment("VOUCHER", this.voucherDetails, this.customerOrder);

                    assertEquals("SUCCESS", voucherPayment.getStatus());
                    assertSame(this.voucherDetails, voucherPayment.getPaymentData());
                    assertSame(this.customerOrder, voucherPayment.getOrder());
                    assertNotNull(voucherPayment.getId());

                },
                () -> {
                    Payment bankPayment = new Payment("BANK", this.bankDetails, this.customerOrder);
                    assertEquals("SUCCESS", bankPayment.getStatus());
                    assertSame(this.bankDetails, bankPayment.getPaymentData());
                    assertSame(this.customerOrder, bankPayment.getOrder());
                    assertNotNull(bankPayment.getId());
                }
        );
    }


}
