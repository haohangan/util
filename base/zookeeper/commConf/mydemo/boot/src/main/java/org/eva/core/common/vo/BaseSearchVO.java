package org.eva.core.common.vo;

/**
 * 数据库分页搜索vo
 * 
 * @author 97617
 *
 */
public class BaseSearchVO {
	private int pageSize;
	private int page;

	private int start;

	/**
	 * 获取查询的起始值
	 * 
	 * @return start
	 */
	public int getStart() {
		start = (getPage() - 1) * pageSize;
		return start;
	}

	/**
	 * 获取分页大小，默认为10
	 * 
	 * @return
	 */
	public int getPageSize() {
		if (pageSize <= 0) {
			pageSize = 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 查询第几页
	 * 
	 * @return
	 */
	public int getPage() {
		if (page <= 0) {
			page = 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
