package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.LineItemDTO;
import br.com.brasilprev.api.model.LineItem;
import org.springframework.stereotype.Component;

@Component
public class LineItemMapper implements Mapper<LineItemDTO, LineItem> {

    @Override
    public LineItem convertDtoToModel(LineItemDTO dto) {
        //todo
        return null;
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
