package com.muhammedsosun.atm.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class KdvDTO {
    private Integer id;
    private Double amount;
    private Double kdvAmount;
    private Double kdvRate;
    private Double totalAmount;
    private String receiptNumber;
    private LocalDate transactionDate;
    private String description;
    private String exportFormat;

    public boolean isValid() {
        return amount != null && kdvRate != null && amount > 0 && kdvRate >= 0 && transactionDate != null;
    }

    public void calculateTotals() {
        this.kdvAmount = amount * kdvRate / 100;
        this.totalAmount = amount + this.kdvAmount;
    }
    public String toExportString(){
        return String.format("""
                Fiş No     : %s
                Tarih      : %s
                Açıklama   : %s
                Tutar      : %.2f ₺
                KDV Oranı  : %% %.1f
                KDV Tutarı : %.2f ₺
                Genel Toplam: %.2f ₺
                """,
                receiptNumber,
                transactionDate,
                description != null ? description : "-",
                amount,
                kdvRate,
                kdvAmount,
                totalAmount);
    }

}
