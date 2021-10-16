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
@DynamoDBTable(tableName = "Payment")
public class PaymentModel {

    @Id
    private PaymentId id;
    private Integer accountNumber;
    private Integer agency;
    private BigDecimal amount;
    private Integer bankCode;
    private Integer brandCode;
    private BigDecimal discount;
    private BigDecimal netAmount;
    private String type;

    @DynamoDBHashKey(attributeName = "paymentId")
    public Long getPaymentId() {
        return id != null ? id.getPaymentId() : null;
    }

    public void setPaymentId(Long paymentId) {
        if(id == null) id = new PaymentId();
        this.id.setPaymentId(paymentId);
    }

    @DynamoDBRangeKey(attributeName = "pvCode")
    public String getPvCode() {
        return id != null ? id.getPvCode() : null;
    }
    public void setPvCode(String pvCode) {
        if(id == null) id = new PaymentId();
        id.setPvCode(pvCode);
    }

    @DynamoDBAttribute(attributeName = "accountNumber")
    public Integer getAccountNumber() { return accountNumber; }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @DynamoDBAttribute(attributeName = "agency")
    public Integer getAgency() { return agency; }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    @DynamoDBAttribute(attributeName = "amount")
    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute(attributeName = "bankCode")
    public Integer getBankCode() { return bankCode; }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    @DynamoDBAttribute(attributeName = "brandCode")
    public Integer getBrandCode() { return brandCode; }

    public void setBrandCode(Integer brandCode) {
        this.brandCode = brandCode;
    }

    @DynamoDBAttribute(attributeName = "discount")
    public BigDecimal getDiscount() { return discount; }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @DynamoDBAttribute(attributeName = "netAmount")
    public BigDecimal getNetAmount() { return netAmount; }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    @DynamoDBAttribute(attributeName = "type")
    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }
}
