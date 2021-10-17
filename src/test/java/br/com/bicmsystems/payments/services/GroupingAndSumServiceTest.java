package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.dtos.SaleDTO;
import br.com.bicmsystems.payments.dtos.SaleSummaryDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GroupingAndSumServiceTest {

    List<SaleDTO> salesDTO = Arrays.asList(
        new SaleDTO("CV1", "RV1", "DEBIT", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(100), BigDecimal.valueOf(900.0), 1),
        new SaleDTO("CV2", "RV1", "DEBIT", BigDecimal.valueOf(2000.0), BigDecimal.valueOf(200), BigDecimal.valueOf(1800.0), 1),
        new SaleDTO("CV3", "RV1", "DEBIT", BigDecimal.valueOf(5000.0), BigDecimal.valueOf(500), BigDecimal.valueOf(4500.0), 1),
        new SaleDTO("CV4", "RV2", "DEBIT", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(100), BigDecimal.valueOf(900.0), 1),
        new SaleDTO("CV5", "RV2", "DEBIT", BigDecimal.valueOf(2000.0), BigDecimal.valueOf(200), BigDecimal.valueOf(1800.0), 1),
        new SaleDTO("CV6", "RV3", "DEBIT", BigDecimal.valueOf(5000.0), BigDecimal.valueOf(500), BigDecimal.valueOf(4500.0), 1),
        new SaleDTO("CV7", "RV4", "CREDIT", BigDecimal.valueOf(11000.0), BigDecimal.valueOf(100), BigDecimal.valueOf(1900.0), 1),
        new SaleDTO("CV8", "RV4", "CREDIT", BigDecimal.valueOf(12000.0), BigDecimal.valueOf(200), BigDecimal.valueOf(11800.0), 1),
        new SaleDTO("CV9", "RV4", "CREDIT", BigDecimal.valueOf(15000.0), BigDecimal.valueOf(500), BigDecimal.valueOf(14500.0), 1),
        new SaleDTO("CV10", "RV5", "CREDIT", BigDecimal.valueOf(15000.0), BigDecimal.valueOf(500), BigDecimal.valueOf(14500.0), 1)
    );

    private SaleSummaryDTO toSaleSummaryDTO(SaleDTO sale) {
        return SaleSummaryDTO.builder()
            .rvNumber(sale.getRvNumber())
            .type(sale.getType())
            .build();
    }

    @Test
    public void testGroupingData() {

        Map<SaleSummaryDTO, DoubleSummaryStatistics> totalAmount =
                salesDTO.stream().collect(groupingBy(this::toSaleSummaryDTO,
                        summarizingDouble(ss -> ss.getAmount().doubleValue())));

        Map<SaleSummaryDTO, DoubleSummaryStatistics> totalDiscount =
                salesDTO.stream().collect(groupingBy(this::toSaleSummaryDTO,
                        summarizingDouble(ss -> ss.getDiscount().doubleValue())));

        Map<SaleSummaryDTO, DoubleSummaryStatistics> totalNetAmount =
                salesDTO.stream().collect(groupingBy(this::toSaleSummaryDTO,
                        summarizingDouble(ss -> ss.getNetAmount().doubleValue())));

        Map<SaleSummaryDTO, Long> totalCountSales =
                salesDTO.stream().collect(groupingBy(this::toSaleSummaryDTO,
                        Collectors.counting()));

        List<SaleSummaryDTO> saleSummaryDTOS = new ArrayList<>();

        totalAmount.forEach((ss1, amount) -> {
            totalDiscount.forEach((ss2, discount) -> {
                if(ss1.equals(ss2)) {
                    totalNetAmount.forEach((ss3, netAmount) -> {
                        if(ss1.equals(ss3)) {
                            totalCountSales.forEach((ss4, count) -> {
                                if(ss1.equals(ss4)) {
                                    SaleSummaryDTO dto = ss1.clone();
                                    dto.setTotalAmount(amount.getSum());
                                    dto.setTotalDiscount(discount.getSum());
                                    dto.setTotalNetAmount(netAmount.getSum());
                                    dto.setTotalSalesReceipt(Math.toIntExact(count));
                                    saleSummaryDTOS.add(dto);
                                }
                            });
                        }
                    });
                }
            });
        });

        saleSummaryDTOS.forEach(s -> System.out.println(s));

    }


}
