package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.dto.LineItemDTO;
import br.com.brasilprev.api.model.LineItem;
import org.springframework.stereotype.Component;

@Component
public class LineItemMapper implements Mapper<LineItemDTO, LineItem> {

    @Override
    @Deprecated
    public LineItem convertDtoToModel(LineItemDTO dto) {
        return null;
    }

    public LineItem convertDtoToModel(LineItemDTO dto, Order order, Product product) {
       return new LineItem(
               dto.getId(),
               order,
               product,
               product.getPrice(),
               dto.getQuantity());
    }

    @Override
    public LineItemDTO convertModelToDto(LineItem model) {
        return new LineItemDTO(
                model.getId(),
                model.getOrder().getId(),
                model.getProduct().getId(),
                model.getQuantity(),
                model.getProduct().getName(),
                model.getProduct().getSku(),
                model.getSellingPrice());
    }
}
