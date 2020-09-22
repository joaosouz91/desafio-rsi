package br.com.brasilprev.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 1850788597676446170L;

    private Long id;

    @NotNull
    private Long idCostumer;

    @NotNull
    private Long idDeliveryAddress;

    private LocalDate creationDate;

    private LocalDate endDate;

    private List<LineItemDTO> productList;

    private Double discount;

    private BigDecimal totalPrice;

    private String status;


}