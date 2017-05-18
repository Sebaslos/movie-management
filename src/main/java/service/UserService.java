package service;


import beans.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class UserService extends AbstractService<User> {

	public User authenticate(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from User where username=:username and password=:password";
		Query query = session.createQuery(hql)
				.setParameter("username", username)
				.setParameter("password", password);
		User user = (User) query.getSingleResult();
		session.close();
		return user;
	}

}
