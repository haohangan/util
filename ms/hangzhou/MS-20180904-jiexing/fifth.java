package ms;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 按层输出二叉树
 * @author 97617
 *
 */
public class Fifth {
	
	/**
	 * 定义一个二叉树
	 * @author 97617
	 *
	 * @param <T>
	 */
	@Data
	static class Node<T>{
		T value;
		Node<T> left;
		Node<T> right;
		public Node(T value) {
			super();
			this.value = value;
		}
	}
	
	
	
	/**
	 * 获得第n层的数据
	 * @param root
	 * @param n
	 * @return
	 */
	static List<Node<String>> getN(Node<String> node,int n){
		List<Node<String>> list = new ArrayList<>();
		if(node==null) {
			return list;
		}
		if(n==1){//n为1时，获取当前元素
			list.add(node);
		}else {//n>1时，走向下一层，直到n==1时，才会返回数据
			list.addAll(getN(node.getLeft(),n-1));
			list.addAll(getN(node.getRight(),n-1));
		}
		return list;
	}
	
	
	/**
	 * 按层输出二叉树
	 * @param root
	 */
	static void print(Node<String> root) {
		for(int i = 1;;i++) {
			List<Node<String>> list = getN(root,i);
			if(list.isEmpty()) {
				break;
			}
			list.forEach(c->{
				System.out.print(c.getValue()+" ");
			});
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Node<String> root = new Node<String>("A");
		root.setLeft(new Node<String>("B"));
		root.setRight(new Node<String>("C"));
		
		root.getLeft().setLeft(new Node<String>("D"));
		root.getLeft().setRight(new Node<String>("E"));
		
		root.getRight().setLeft(new Node<String>("F"));
		root.getRight().setRight(new Node<String>("G"));
		
		root.getLeft().getLeft().setLeft(new Node<String>("H"));
		root.getLeft().getLeft().setRight(new Node<String>("I"));
		
		print(root);
		
	}
}