package br.com.bicmsystems.payments.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@DynamoDBDocument
public class PaymentId implements Serializable {

//    @DynamoDBHashKey(attributeName = "paymentId")
//    private Long paymentId;
//
//    @DynamoDBRangeKey(attributeName = "pvCode")
//    private String pvCode;
//
//    public PaymentPKModel() {
//    }
//
//    public PaymentPKModel(Long paymentId, String pvCode) {
//        this.paymentId = paymentId;
//        this.pvCode = pvCode;
//    }
//
//    public Long getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(Long paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public String getPvCode() {
//        return pvCode;
//    }
//
//    public void setPvCode(String pvCode) {
//        this.pvCode = pvCode;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PaymentPKModel that = (PaymentPKModel) o;
//        return Objects.equals(paymentId, that.paymentId) && Objects.equals(pvCode, that.pvCode);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(paymentId, pvCode);
//    }
}
