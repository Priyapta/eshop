package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    private final String id;
    private final String method;
    private String status;
    private final Order order;
    private final Map<String, String> paymentData;

    public Payment(String method, Map<String, String> paymentData, Order order) {
        if (order == null) {
            throw new NullPointerException("Order tidak boleh null");
        }
        if (paymentData == null) {
            throw new NullPointerException("Payment data tidak boleh null");
        }


        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Metode pembayaran tidak valid: " + method);
        }

        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
        this.status = PaymentStatus.SUCCESS.getValue(); // Status default

        validatePaymentData();
    }

    private void validatePaymentData() {
        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
            validateVoucherPayment();
        } else if (this.method.equals(PaymentMethod.BANK.getValue())) {
            validateBankPayment();
        }
    }

    private void validateVoucherPayment() {

        String voucherCode = paymentData.get("voucherCode");

        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            this.status = PaymentStatus.REJECTED.getValue();
            return;
        }

        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        if (digitCount != 8) {
            this.status = PaymentStatus.REJECTED.getValue();
        }

    }



    private void validateBankPayment() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || referenceCode == null || referenceCode.trim().isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }



    public void setStatus(String status) {
        String[] validStatus = {"REJECTED", "SUCCESS"};
        if (Arrays.stream(validStatus).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException("Status tidak valid: " + status);
        }
        this.status = status;
    }
}
