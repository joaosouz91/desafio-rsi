package br.com.brasilprev.api.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_product")
public class OrderProduct implements Model, Serializable {

    @EmbeddedId
    private OrderProductId id;

    private BigDecimal price;

    private Double discount;

}



