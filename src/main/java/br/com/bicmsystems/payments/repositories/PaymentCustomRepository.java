package br.com.bicmsystems.payments.repositories;

import br.com.bicmsystems.payments.entities.PaymentModel;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.util.json.Jackson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentCustomRepository {

    static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static final DynamoDB dynamoDB = new DynamoDB(client);
    static final String tableName = "Payment";

    static final List<Long> pvs = List.of(202108010000000003L, 202108010000000004L, 202108010000000005L);
    static final String pvCode = "1234567890";

    public List<PaymentModel> getPaymentByBatchGet() {

        List<PaymentModel> result = new ArrayList<>();
        final List<Object> primaryKeys = Arrays.asList(
                202108010000000003L, "1234567890",
                202108010000000004L, "1234567890",
                202108010000000005L, "1234567890");

        try {

            TableKeysAndAttributes paymentTableKeysAndAttributes = new TableKeysAndAttributes(tableName);
            // Add a partition key
            paymentTableKeysAndAttributes.addHashAndRangePrimaryKeys("paymentId", "pvCode",
                    primaryKeys.toArray());

            // Making the request
            BatchGetItemOutcome outcome = dynamoDB.batchGetItem(paymentTableKeysAndAttributes);

            Map<String, KeysAndAttributes> unprocessed = null;

            do {
                for (String tableName : outcome.getTableItems().keySet()) {
                    System.out.println("Items in table " + tableName);
                    List<Item> items = outcome.getTableItems().get(tableName);
                    for (Item item : items) {

                        PaymentModel model = Jackson.fromJsonString(item.toJSON(), PaymentModel.class);
                        result.add(model);

                        System.out.println(item.toJSONPretty());
                    }
                }

                // Check for unprocessed keys which could happen if you exceed
                // provisioned
                // throughput or reach the limit on response size.
                unprocessed = outcome.getUnprocessedKeys();

                if (unprocessed.isEmpty()) {
                    System.out.println("No unprocessed keys found");
                }
                else {
                    System.out.println("Retrieving the unprocessed keys");
                    outcome = dynamoDB.batchGetItemUnprocessed(unprocessed);
                }

            } while (!unprocessed.isEmpty());

        }
        catch (Exception e) {
            System.err.println("Failed to retrieve items.");
            System.err.println(e.getMessage());
        }

        return result;
    }

}
