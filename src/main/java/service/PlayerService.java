package service;


import beans.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class PlayerService extends AbstractService<Player> {

	public Player findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Player where name=:name";
		Query query = session.createQuery(hql).setParameter("name", name);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

}
