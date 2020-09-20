package br.com.brasilprev.api.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "line_item")
public class LineItem implements Model, Serializable {

    @Id
    @ManyToOne
    //@MapsId("id")
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    private Long quantity;

}



