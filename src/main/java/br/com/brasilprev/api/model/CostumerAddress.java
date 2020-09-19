package br.com.brasilprev.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "costumer_address")
public class CostumerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_costumer", referencedColumnName="id", nullable=false)
    private Costumer costumer;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String cep;

    private String district;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.street)
                .append(", ")
                .append(this.number)
                .append("\nCEP")
                .append(this.cep)
                .append("\n")
                .append(this.district)
                .append(" - ")
                .append(this.city)
                .append("/")
                .append(this.state)
                .append("\n")
                .append(this.country)
                    .toString();
    }
}
