package br.com.brasilprev.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name="permission")
public class Permission {

	@Id
	private Long id;
	
	private String description;

}
