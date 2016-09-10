package zk.utils.vo;

import java.util.ArrayList;
import java.util.List;

public class PathNode {
	private String path;
	private List<NodeInfo> nodelist;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<NodeInfo> getNodelist() {
		return nodelist;
	}

	public void setNodelist(List<NodeInfo> nodelist) {
		this.nodelist = nodelist;
	}

	/**
	 * 节点增加信息
	 * @param ni
	 */
	public void addNode(NodeInfo ni) {
		if (nodelist != null) {
			nodelist.add(ni);
			return;
		}
		nodelist = new ArrayList<>();
		nodelist.add(ni);
	}
}
