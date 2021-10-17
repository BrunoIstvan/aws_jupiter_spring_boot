package br.com.bicmsystems.payments.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SaleDTO {

    private String saleReceipt;
    private String rvNumber;
    private String type;
    private BigDecimal amount;
    private BigDecimal discount;
    private BigDecimal netAmount;
    private Integer totalSalesReceipt;

}
