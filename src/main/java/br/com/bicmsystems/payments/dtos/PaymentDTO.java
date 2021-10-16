package br.com.bicmsystems.payments.dtos;

import br.com.bicmsystems.payments.validators.OnlyAlphanumericConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private Long paymentId;
    @OnlyAlphanumericConstraint
    private String pvCode;
    private Integer accountNumber;
    private Integer agency;
    private BigDecimal amount;
    private Integer bankCode;
    private Integer brandCode;
    private BigDecimal discount;
    private BigDecimal netAmount;
    @OnlyAlphanumericConstraint
    private String type;

    @JsonIgnore
    private List<PaymentDTO> negotiations;

}
