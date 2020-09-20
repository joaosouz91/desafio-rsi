package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<OrderDTO, Order> {

    @Autowired
    private LineItemMapper lineItemMapper;

    @Override
    public Order convertDtoToModel(OrderDTO dto) {
        return null;
    }

    @Override
    public OrderDTO convertModelToDto(Order model) {
        return new OrderDTO(
                model.getId(),
                model.getCostumer().getId(),
                model.getCostumerAddress().getId(),
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
