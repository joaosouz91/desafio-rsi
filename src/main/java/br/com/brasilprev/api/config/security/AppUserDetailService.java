package br.com.brasilprev.api.config.security;

import br.com.brasilprev.api.model.UserAccount;
import br.com.brasilprev.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserAccount> userAccountOptional = userAccountRepository.findByEmail(email);
		UserAccount userAccount = userAccountOptional.orElseThrow(()-> new UsernameNotFoundException(""));
		return new SystemUser(userAccount, getPermissions(userAccount));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserAccount userAccount) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		userAccount.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}
