package com.eva.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="operation",schema="HIB")
public class Operation  implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private long opid;
	private Operation fop;
	private String opName;
	private String opUrl;
	private String opType;
	
	private Set<Role> roles;
	
//	private Set<Operation> childNodes;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "opid", unique = true, nullable = false, precision = 11, scale = 0)
	public long getOpid() {
		return opid;
	}

	public void setOpid(long opid) {
		this.opid = opid;
	}

//	@Column(name = "opt_fop")
	@OneToOne
   @JoinColumn(name="opid")
	public Operation getFop() {
		return fop;
	}

	public void setFop(Operation fop) {
		this.fop = fop;
	}

	@Column(name = "opt_name", length = 100)
	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	@Column(name = "opt_url", length = 200)
	public String getOpUrl() {
		return opUrl;
	}

	public void setOpUrl(String opUrl) {
		this.opUrl = opUrl;
	}

	@Column(name = "opt_type", length = 100)
	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	@Override
	public String toString() {
		return "Operation [opid=" + opid +", opName=" + opName + ", opUrl=" + opUrl + ", opType="
				+ opType + "]";
	}

	@ManyToMany
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

//	public Set<Operation> getChildNodes() {
//		return childNodes;
//	}
//
//	public void setChildNodes(Set<Operation> childNodes) {
//		this.childNodes = childNodes;
//	}
	
}
