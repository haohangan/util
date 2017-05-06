package deadlock;

public class TestLock {

	public static void main(String[] args) {
		Resource r1 = new Resource(1);
		Resource r2 = new Resource(2);
		ResUser user1 = new ResUser(r1, r2);
		ResUser user2 = new ResUser(r2, r1);
		new Thread(user1).start();
		new Thread(user2).start();
	}
}
