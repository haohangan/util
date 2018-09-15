package ms;

import java.util.Stack;

/**
 * 输入一个乱序的栈，返回一个排序过（栈顶最大，栈底最小）的栈
 * @author 97617
 * https://blog.csdn.net/fightforyourdream/article/details/20275353
 */
public class Fourth2 {

	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(5);
		stack.push(7);
		stack.push(3);
		stack.push(1);
		stack.push(2);
		stack.push(4);
		Stack<Integer> sorted = sort(stack);
		System.out.println(sorted);
	}
	
	
	static Stack<Integer> sort(Stack<Integer> stack){
		Stack<Integer> sorted = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			while(!sorted.isEmpty() && sorted.peek()>temp) {
				stack.push(sorted.pop());
			}
			sorted.push(temp);
		}
		return sorted;
	}
}
