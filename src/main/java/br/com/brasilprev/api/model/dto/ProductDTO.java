package br.com.brasilprev.api.model.dto;

import br.com.brasilprev.api.model.enumerator.ModelStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    @Size(max = 20)
    private String sku;

    @NotNull
    @Digits(integer=10, fraction=2)
    private BigDecimal price;

    @NotNull
    private ModelStatus status;

}
