package br.com.brasilprev.api.service;

import br.com.brasilprev.api.dto.OrderDTO;
import br.com.brasilprev.api.dto.LineItemDTO;
import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.LineItem;
import br.com.brasilprev.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageSource messageSource;

    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findAll();
        if(orderList == null || orderList.isEmpty()) throw new EmptyResultDataAccessException(1);
        return orderList.stream().map(this::buildDTO).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepository.findOne(id);
        if(order == null) throw new EmptyResultDataAccessException(1);
        return this.buildDTO(order);
    }

    public Order create(Order order) {
        if(order.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        if(order.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }

    private OrderDTO buildDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCostumer().getId(),
                order.getCostumerAddress().getId(),
                order.getCreationDate(),
                order.getEndDate(),
                this.buildLineItemDTO(order.getLineItemList()),
                order.getDiscount(),
                order.getTotalPrice(),
                order.getStatus().toString());
    }

    private List<LineItemDTO> buildLineItemDTO(List<LineItem> lineItemList) {

        final List<LineItemDTO> lineItemDTOList = new ArrayList<>();

        for (LineItem lineItem : lineItemList) {
            final LineItemDTO lineItemDTO = new LineItemDTO();
            lineItemDTO.setIdProduct(lineItem.getProduct().getId());
            lineItemDTO.setName(lineItem.getProduct().getName());
            lineItemDTO.setSKU(lineItem.getProduct().getSku());
            lineItemDTO.setSellingPrice(lineItem.getSellingPrice());
            lineItemDTOList.add(lineItemDTO);
        }

        return lineItemDTOList;
    }

    private BigDecimal getOrderTotalWithDiscount(BigDecimal total, Double discount) {
        return total
                .subtract(total.multiply(BigDecimal.valueOf(discount/100)))
                .setScale(2, BigDecimal.ROUND_FLOOR);
    }

    private BigDecimal getSellingPriceSum(List<LineItem> lineItemList) {
        return lineItemList.stream()
                .map(item -> item.getSellingPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getCurrentPriceSum(List<LineItem> lineItemList) {
        return lineItemList.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
