package br.com.brasilprev.api.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id", "name", "description", "sku", "price", "enabled"
})
public class ProductDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 6643736135217704754L;
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String sku;

    @NotNull
    private BigDecimal price;

    @NotNull
    private boolean enabled;

}
