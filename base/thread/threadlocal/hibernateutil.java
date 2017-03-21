package demo.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author gg
 * @date 2017年3月21日下午2:59:31
 * @desc
 */
public class HibernateUtil {
	private static final SessionFactory factory;

	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static ThreadLocal<Session> localSession = new ThreadLocal<>();

	public static Session currentSession() {
		Session session = localSession.get();
		if (session == null) {
			session = factory.openSession();
			localSession.set(session);
		}
		return session;
	}

	public static void closeSession() {
		Session session = localSession.get();
		localSession.set(null);
		if (session != null) {
			session.close();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(HibernateUtil.currentSession());
	}
}
