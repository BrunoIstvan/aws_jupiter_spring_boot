package br.com.bicmsystems.payments.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentId implements Serializable {

    private Long paymentId;

    private String pvCode;

    @DynamoDBHashKey(attributeName = "paymentId")
    public Long getPaymentId() { return paymentId; }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @DynamoDBRangeKey(attributeName = "pvCode")
    public String getPvCode() { return pvCode; }

    public void setPvCode(String pvCode) {
        this.pvCode = pvCode;
    }

}
