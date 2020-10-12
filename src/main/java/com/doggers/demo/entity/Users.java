package com.doggers.demo.entity;

import java.io.Serializable;
import java.util.Collection;

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
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String lastname;
	
	@Column(length = 50)
	private String phone;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Column(name = "password", length = 60)
	private String password;

	private Boolean enabled;
	
	private Long rol;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name="users_roles", joinColumns = @JoinColumn(name="users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
	private Collection<Role> roles;
	
	
	public Users(String name, String lastname, String phone, String email, String password, Collection<Role> roles) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}
	
	
	
	public Long getRol() {
		return rol;
	}



	public void setRol(Long long1) {
		this.rol = long1;
	}



	public Users() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
