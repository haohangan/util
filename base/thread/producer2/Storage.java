package ti2.producer2;

import java.util.LinkedList;

public class Storage {
	private final static int MAX = 6;
	private LinkedList<Resource> list = new LinkedList<Resource>();

	public void push(Resource r) {
		synchronized (list) {
			try {
				while (list.size() == MAX) {// 仓库已满
					System.out.println("仓库已满！不可增加库存");
					list.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.add(r);
			System.out.println(r + "入库成功!");
			list.notifyAll();
		}
	}

	public Resource pop() {
		Resource r = null;
		synchronized (list) {
			try {
				while (list.size() == 0) {
					System.out.println("仓库为空，等待生产！");
					list.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			r = list.pop();
			// System.out.println("消费了：" + r);
			list.notifyAll();
		}
		return r;
	}
}
