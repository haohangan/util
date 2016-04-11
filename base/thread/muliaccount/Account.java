package ti2.muliaccount;

public class Account {
	private Integer id;
	private Integer cash;

	public Account(Integer id, Integer cash) {
		super();
		this.id = id;
		this.cash = cash;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", cash=" + cash + "]";
	}

}
