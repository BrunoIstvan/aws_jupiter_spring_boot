package br.com.bicmsystems.payments.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "PaymentCreditOrder")
public class PaymentCreditOrderModel {

    @Id
    private PaymentCreditOrderId id;
    private String pvCode;
    private BigDecimal amount;
    private BigDecimal discount;
    private BigDecimal netAmount;
    private String type;

    @DynamoDBHashKey(attributeName = "paymentId")
    public Long getPaymentId() {
        return id != null ? id.getPaymentId() : null;
    }

    public void setPaymentId(Long paymentId) {
        if(id == null) { id = new PaymentCreditOrderId(); }
        this.id.setPaymentId(paymentId);
    }

    @DynamoDBRangeKey(attributeName = "creditOrderTimestamp")
    public String getCreditOrderTimestamp() {
        return id != null ? id.getCreditOrderTimestamp() : null;
    }

    public void setCreditOrderTimestamp(String creditOrderTimestamp) {
        if(id == null) { id = new PaymentCreditOrderId(); }
        this.id.setCreditOrderTimestamp(creditOrderTimestamp);
    }

    @DynamoDBAttribute(attributeName = "pvCode")
    public String getPvCode() { return pvCode; }

    public void setPvCode(String pvCode) {
        this.pvCode = pvCode;
    }

    @DynamoDBAttribute(attributeName = "amount")
    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute(attributeName = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @DynamoDBAttribute(attributeName = "netAmount")
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
