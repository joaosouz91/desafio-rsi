package br.com.brasilprev.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id", "idOrder", "idProduct", "quantity", "name", "SKU", "sellingPrice"
})
public class LineItemDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 1181567578235409725L;

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    @Size(max = 20)
    private Long idOrder;

    @NotNull
    @Size(max = 20)
    private Long idProduct;

    @NotNull
    @Size(max = 10)
    private Long quantity;

    private String name;

    private String sku;

    private BigDecimal sellingPrice;

}
