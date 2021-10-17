package br.com.bicmsystems.payments.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SaleSummaryDTO implements Cloneable {

    private String rvNumber;
    private Integer totalSalesReceipt;
    private String type;
    private Double totalAmount;
    private Double totalDiscount;
    private Double totalNetAmount;

    @Override
    public SaleSummaryDTO clone() {
        try {
            return (SaleSummaryDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
