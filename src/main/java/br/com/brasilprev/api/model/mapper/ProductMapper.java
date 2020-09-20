package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.ProductDTO;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.enumerator.ModelStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductDTO, Product> {

    @Override
    public Product convertDtoToModel(ProductDTO dto) {
        return new Product(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getSku(),
                dto.getPrice(),
                dto.isEnabled() ? ModelStatus.ENABLED : ModelStatus.DISABLED);
    }

    @Override
    public ProductDTO convertModelToDto(Product model) {
        return new ProductDTO(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getSku(),
                model.getPrice(),
                model.getStatus().getBoolean());
    }
}
