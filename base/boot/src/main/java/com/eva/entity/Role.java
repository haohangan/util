package com.eva.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="role",schema="HIB")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long rid;
	private String roleName;
	private String roleType;
	private Set<Operation> opts;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "rid", unique = true, nullable = false, precision = 11, scale = 0)
	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	@Column(name = "role_name", length = 100)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_type", length = 100)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@ManyToMany
	public Set<Operation> getOpts() {
		return opts;
	}

	public void setOpts(Set<Operation> opts) {
		this.opts = opts;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", roleName=" + roleName + ", roleType=" + roleType + "]";
	}

}
