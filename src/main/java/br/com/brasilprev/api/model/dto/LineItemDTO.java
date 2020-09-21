package br.com.brasilprev.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class LineItemDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 1181567578235409725L;

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private Long idOrder;

    @NotNull
    private Long idProduct;

    @NotNull
    private Long quantity;

    private String name;

    private String SKU;

    private BigDecimal sellingPrice;

}
