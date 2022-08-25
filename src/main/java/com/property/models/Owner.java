package com.property.models;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import com.property.controllers.CustomerController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="Owner")
@NoArgsConstructor
@Getter 
@Setter
public class Owner implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ownerId;
	
	@Column(name = "Owner_name" , nullable = false, length = 100)
	private String name;
	private String contactdetail;
	private String password;
	
	@OneToMany(mappedBy = "owner", cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<PropertyDetails> property = new ArrayList<>();

	@Override
	public String getUsername() {
		return this.contactdetail;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
