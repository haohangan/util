package curator_y.common_configuration.buss.controller.requestvo;

public class PageVO {
	private int page;
	private int size;
	private String sort;

	public int getPage() {
		if (page < 0) {
			return 0;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		if (size <= 0) {
			return 10;
		}
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
