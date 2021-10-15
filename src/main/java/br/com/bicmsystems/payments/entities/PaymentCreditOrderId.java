package br.com.bicmsystems.payments.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreditOrderId implements Serializable {

    private Long paymentId;

    private String creditOrderTimestamp;

    @DynamoDBHashKey(attributeName = "paymentId")
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @DynamoDBRangeKey(attributeName = "creditOrderTimestamp")
    public String getCreditOrderTimestamp() {
        return creditOrderTimestamp;
    }

    public void setCreditOrderTimestamp(String creditOrderTimestamp) {
        this.creditOrderTimestamp = creditOrderTimestamp;
    }

}
