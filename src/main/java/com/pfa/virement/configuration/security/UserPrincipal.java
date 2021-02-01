package com.pfa.virement.configuration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfa.virement.entities.Abonne;



public class UserPrincipal implements UserDetails {

	
	
	private static final long serialVersionUID = 1L;
	
	Abonne abonne;
	
	@Autowired
	public UserPrincipal(Abonne abonne) {
		super();
		this.abonne=abonne;

	}



	@Override
	public String getPassword() {
		
		return this.abonne.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.abonne.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		GrantedAuthority authority= new SimpleGrantedAuthority("ROLE_User"); 
//		authorities.add(authority);
//		return authorities;
		return null;
	}

}
