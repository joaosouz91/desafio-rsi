package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "\"order\"")
public class Order implements Model, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_costumer", referencedColumnName="id", nullable=false)
    private Costumer costumer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_costumer_address", referencedColumnName="id", nullable=false)
    private CostumerAddress costumerAddress;

    @NotNull
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItemList;

    @Column(name = "order_discount")
    private Double discount;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @NotNull
    private OrderStatus status;

}
