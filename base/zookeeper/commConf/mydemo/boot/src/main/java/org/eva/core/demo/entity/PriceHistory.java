package org.eva.core.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRICE_HISTORY", schema = "test")
public class PriceHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private int phid;
	private int itemId;
	private String itemName;
	private Double price;
	private Date phdate;
	private String createUser;

	@Id
	@Column(name = "phid", unique = true, nullable = false, precision = 11, scale = 0)
	public int getPhid() {
		return phid;
	}

	@Column(name = "item_id", nullable = false, precision = 11, scale = 0)
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_name",length=100)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "phdate")
	public Date getPhdate() {
		return phdate;
	}

	public void setPhdate(Date phdate) {
		this.phdate = phdate;
	}

	@Column(name = "create_user")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public void setPhid(int phid) {
		this.phid = phid;
	}

}
