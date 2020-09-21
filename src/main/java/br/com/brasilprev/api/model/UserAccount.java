package br.com.brasilprev.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name= "user_account")
public class UserAccount {
	
	@Id
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_account_permission",
		joinColumns = @JoinColumn(name = "id_user_account"),
		inverseJoinColumns = @JoinColumn(name="id_permission")
	)
	private List<Permission> permissions;
	
	
}
