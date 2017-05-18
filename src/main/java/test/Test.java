package test;


import beans.User;
import org.hibernate.Session;
import util.HibernateUtil;

public class Test {

	public void test() {
		User user = new User();
		user.setUsername("sccc");
		user.setPassword("lol");


		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(user);

		session.getTransaction().commit();
		session.close();


	}

	public static void main(String[] args) {
		Test test = new Test();
		test.test();
	}

}
