package br.com.bicmsystems.payments.dtos;

import br.com.bicmsystems.payments.enums.NegotiationType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditOrderDTO {

    private String creditOrderTimestamp;
    private BigDecimal amount;
    private String pvCode;
    private NegotiationType negotiationType;

}
