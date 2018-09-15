package ms;

import java.util.Objects;

/*
*单向链表，获取倒数第n个节点（双指针使用）
**/
public class Second {
	static class LinkNode{
		String value;
		
		LinkNode next;
		
		public LinkNode(String value) {
			super();
			this.value = value;
		}

		public LinkNode(String value, LinkNode next) {
			super();
			this.value = value;
			this.next = next;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public LinkNode getNext() {
			return next;
		}

		public void setNext(LinkNode next) {
			if(next==this) {
				throw new RuntimeException("不可以设置的节点"+next.value);
			}
			this.next = next;
		}

	}
	
	static LinkNode getFirstNode() {
		LinkNode first = new LinkNode("first");
		
		LinkNode current = first;

		current.setNext(new LinkNode("second"));
		current = current.getNext();
		
		current.setNext(new LinkNode("third"));
		current = current.getNext();
		
		current.setNext(new LinkNode("fourth"));
		current = current.getNext();
		
		current.setNext(new LinkNode("fifth"));
		
		return first;
	}
	
	static void print(LinkNode node) {
		if(Objects.isNull(node)) {
			System.out.println("empty node");
			return;
		}
		System.out.println(node.getValue());
		while(!Objects.isNull(node.getNext())) {
			node = node.getNext();
			System.out.println(node.getValue());
		}
	}
	
	static void print(LinkNode node,int n) {
		if(Objects.isNull(node)) {
			System.out.println("empty node");
			return;
		}
		LinkNode p1 = node;
		LinkNode p2 = node;
		for(int i = 0;i<3;i++) {
			p2 = p2.getNext();
		}
		
		while(p2.getNext()!=null) {
			p1 = p1.getNext();
			p2 = p2.getNext();
		}
		System.out.println("（从0开始数起）倒数第n个节点为 ："+p1.getValue());
		System.out.println("（从1开始数起）倒数第n个节点为 ："+p1.getNext().getValue());
	}
	
	
	
	
	public static void main(String[] args) {
		LinkNode node = getFirstNode();
		print(node);
		print(node,1);
		

	}
}
