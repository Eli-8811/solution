package com.core.solution.bussines;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.core.solution.model.entity.EntityRoleUser;
import com.core.solution.model.entity.EntityUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDetailService implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String email;
	
	private Long phone;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailService(Integer id, String username, String email, String password, Long phone, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.authorities = authorities;
	}
/*
	public static UserDetailService build(EntityUser entityUser) {
		List<GrantedAuthority> authorities = entityUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());	
		return new UserDetailService(entityUser.getUserId(), entityUser.getUsername(), entityUser.getEmail(), entityUser.getPassword(), entityUser.getPhone(), authorities);
	}
*/
	public static UserDetailService build(EntityUser entityUser) {
		
		Set<EntityRoleUser> roles = new HashSet<EntityRoleUser>();
		EntityRoleUser item = new EntityRoleUser();
		
		item.setRoleUserId(1);
		item.setUserId(1);
		item.setRoleId(1);
		item.setName("ROLE_ADMIN");
		item.setEnabled(true);
		item.setCreationAt(new Date());
		item.setModificationAt(new Date());
		roles.add(item);	
		entityUser.setRoles(roles);
		
		List<GrantedAuthority> authorities = entityUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());	
		return new UserDetailService(entityUser.getUserId(), entityUser.getUsername(), entityUser.getEmail(), entityUser.getPassword(), entityUser.getPhone(), authorities);
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}