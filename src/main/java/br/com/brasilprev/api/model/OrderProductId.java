package br.com.brasilprev.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrderProductId implements Serializable {

    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "id_product")
    private Long idProduct;
}
