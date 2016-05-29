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
@Table(name = "tuser",schema="HIB")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long tid;
	private String name;
	private String pwd;
	private Integer age;
	
	private Set<Role> roles;
//	private Set<Operation> opts;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "tid", unique = true, nullable = false, precision = 11, scale = 0)
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pwd", length = 250)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@ManyToMany
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

//	@OneToMany
//	public Set<Operation> getOpts() {
//		return opts;
//	}
//
//	public void setOpts(Set<Operation> opts) {
//		this.opts = opts;
//	}

	@Override
	public String toString() {
		return "AppUser [tid=" + tid + ", name=" + name + ", pwd=" + pwd + ", age=" + age + "]";
	}
	
}
