package br.com.bicmsystems.payments.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "CreditOrder")
@Getter
@Setter
@NoArgsConstructor
public class CreditOrderModel {

    @DynamoDBHashKey
    private String creditOrderTimestamp;

    private BigDecimal amount;

    private String pvCode;

    private String negotiationType;

}
