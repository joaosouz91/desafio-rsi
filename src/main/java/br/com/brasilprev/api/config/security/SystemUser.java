package br.com.brasilprev.api.config.security;

import br.com.brasilprev.api.model.UserAccount;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SystemUser extends User {

	private static final long serialVersionUID = -6301254468246935705L;

	private UserAccount userAccount;
	
	public SystemUser(UserAccount userAccount, Collection<? extends GrantedAuthority> authorities) {
		super(userAccount.getEmail(), userAccount.getPassword(), authorities);
		this.userAccount = userAccount;
	}

}
