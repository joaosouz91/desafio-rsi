package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "costumer_address")
public class CostumerAddress implements Model, Serializable {

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

    @NotNull
    private String district;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    @Column(name = "address_type")
    private AddressType addressType;

    @Override
    @JsonIgnore
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
