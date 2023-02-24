package com.exam.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	private long roleId;
	private String roleName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="role")
	private Set<UserRole> userRole= new HashSet<>();
	
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Role() {
		
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
