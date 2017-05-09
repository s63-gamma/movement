package com.gamma.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rick on 5/9/17.
 */
public class CredentialsRoleMapper implements UserDetailsContextMapper, Serializable {
	@Override
	public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add((GrantedAuthority) () -> "ACTUATOR");
		return new User(s, "", true,true,true, true, authorities);
	}

	@Override
	public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {

	}
}
