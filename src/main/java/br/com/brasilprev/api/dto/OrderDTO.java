package br.com.brasilprev.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO implements AbstractDTO {

    private Long id;
    private Long idCostumer;
    private Long idDeliveryAddress;
    private LocalDate creationDate;
    private LocalDate endDate;
    private List<LineItemDTO> productList;
    private Double discount;
    private BigDecimal totalPrice;
    private String status;

}
