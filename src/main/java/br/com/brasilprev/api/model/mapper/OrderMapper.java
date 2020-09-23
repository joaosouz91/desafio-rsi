package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.*;
import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.model.enumerator.OrderStatus;
import br.com.brasilprev.api.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<OrderDTO, Order> {

    @Autowired
    private LineItemMapper lineItemMapper;

    @Autowired
    private CalculatorService calculatorService;

    @Override
    @Deprecated
    public Order convertDtoToModel(OrderDTO dto) {
        return null;
    }

    public Order convertDtoToModel(OrderDTO dto, Set<Product> productSet, Customer customer) {

        final Order order = new Order();

        final List<LineItem> lineItemList = dto.getProductList()
                .stream()
                .map(lineItemDTO -> {
                    final Product product = productSet.stream()
                            .filter(p -> lineItemDTO.getIdProduct().equals(p.getId()))
                            .findFirst()
                            .orElse(null);
                    if(product != null && product.getStatus().getBoolean()) {
                        return lineItemMapper.convertDtoToModel(lineItemDTO, order, product);
                    }
                    return null;
                }).collect(Collectors.toList());

        final CustomerAddress customerAddress = customer.getAdressList()
                .stream()
                .filter(address -> address.getId().equals(dto.getIdDeliveryAddress()))
                .findFirst()
                .orElse(null);

        final LocalDate creationDate = LocalDate.now();

        final BigDecimal totalPrice = calculatorService.getOrderTotalWithDiscount(
                calculatorService.getCurrentPriceSum(lineItemList),
                dto.getDiscount());

        order.setCustomer(customer);
        order.setCustomerAddress(customerAddress);
        order.setCreationDate(creationDate);
        order.setLineItemList(lineItemList);
        order.setDiscount(dto.getDiscount());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.WAITING_PAYMENT_APPROVAL);

        return order;
    }

    @Override
    public OrderDTO convertModelToDto(Order model) {
        return new OrderDTO(
                model.getId(),
                model.getCustomer().getId(),
                model.getCustomerAddress().getId(),
                model.getCreationDate(),
                model.getEndDate(),
                model.getLineItemList()
                        .stream()
                        .map(lineItemMapper::convertModelToDto)
                        .collect(Collectors.toList()),
                model.getDiscount(),
                model.getTotalPrice(),
                model.getStatus().toString());
    }


}
