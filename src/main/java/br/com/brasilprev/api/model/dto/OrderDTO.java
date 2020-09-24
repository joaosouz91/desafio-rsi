package br.com.brasilprev.api.model.dto;

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
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id", "idCustomer", "idDeliveryAddress", "creationDate", "endDate", "productList", "discount", "totalPrice", "status"
})
public class OrderDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 1850788597676446170L;

    private Long id;

    @NotNull
    @Size(max = 20)
    private Long idCustomer;

    @NotNull
    @Size(max = 20)
    private Long idDeliveryAddress;

    private LocalDate creationDate;

    private LocalDate endDate;

    private List<LineItemDTO> productList;

    @Digits(integer=3, fraction=2)
    private Double discount;

    @Digits(integer=10, fraction=2)
    private BigDecimal totalPrice;

    private String status;


}
