package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.dto.LineItemDTO;
import br.com.brasilprev.api.model.LineItem;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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

        final LineItemDTO lineItemDTO = new LineItemDTO();
        lineItemDTO.setIdProduct(model.getProduct().getId());
        lineItemDTO.setName(model.getProduct().getName());
        lineItemDTO.setSKU(model.getProduct().getSku());
        lineItemDTO.setSellingPrice(model.getSellingPrice());

        return lineItemDTO;
    }
}
