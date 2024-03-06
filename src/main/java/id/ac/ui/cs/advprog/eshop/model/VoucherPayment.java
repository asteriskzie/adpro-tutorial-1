package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;

import java.util.Map;

public class VoucherPayment extends Payment{

    private boolean isValidData(Map<String,String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null) {
            int numericalCount = 0;
            for (int i = 5; i < voucherCode.length(); i++) {
                if (Character.isDigit(voucherCode.charAt(i))) {
                    numericalCount++;
                }
            }

            return voucherCode.length() == 16
                    && voucherCode.startsWith("ESHOP")
                    && numericalCount == 8;
        }

        return false;
    }

    public VoucherPayment(String paymentId, Map<String, String> paymentData) {
        super(paymentId, "Voucher", PaymentStatus.REJECTED.getValue(), paymentData);

        if (isValidData(paymentData)) {
            this.setPaymentStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
