package br.com.brasilprev.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 6643736135217704754L;
    
    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private boolean enabled;

}
