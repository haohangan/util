package serialize.serialize;

import java.io.Serializable;
import java.util.List;

public class ListObject implements Serializable{
	private static final long serialVersionUID = 941368581394354252L;
	private int size;
	private List<?> list;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public ListObject(int size, List<?> list) {
		super();
		this.size = size;
		this.list = list;
	}

	public ListObject() {
		super();
	}

}
