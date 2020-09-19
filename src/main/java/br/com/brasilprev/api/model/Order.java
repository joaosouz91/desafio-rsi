package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Order implements Model {

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
    private LocalDate date;

    @NotNull
    @OneToMany
    private List<OrderProduct> productList;

    @Column(name = "discount")
    private Double discountOnTotalPrice;

    @NotNull
    private OrderStatus status;
}
