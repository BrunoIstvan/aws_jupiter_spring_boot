package br.com.bicmsystems.payments.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PaymentCreditOrderDTO implements Serializable {

    private String creditOrderTimestamp;

    private Long paymentId;

    private String pvCode;

    private BigDecimal amount;

    private BigDecimal discount;

    private BigDecimal netAmount;

    private String type;

}
