package br.com.brasilprev.api.util;

import br.com.brasilprev.api.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

public class CalcUtils {

    public static BigDecimal getOrderTotalWithDiscount(BigDecimal total, Double discount) {
        return total
                .subtract(total.multiply(BigDecimal.valueOf(discount/100)))
                .setScale(2, BigDecimal.ROUND_FLOOR);
    }

    public static BigDecimal getSellingPriceSum(List<LineItem> lineItemList) {
        return lineItemList.stream()
                .map(item -> item.getSellingPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getCurrentPriceSum(List<LineItem> lineItemList) {
        return lineItemList.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
