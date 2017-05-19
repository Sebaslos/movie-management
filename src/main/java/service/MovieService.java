package service;


import beans.Movie;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MovieService extends AbstractService<Movie> {

    public List<Movie> searchMovie(String search) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select distinct m from Movie m inner join m.players " +
                "where upper(m.title) like :search or " +
                "m.year like :search or upper(m.director) like :search or " +
                "upper(m.genre) like :search or upper(name) like :search";
        Query query = session.createQuery(hql)
                .setParameter("search", "%" + search.toUpperCase() + "%");
        List<Movie> list = query.getResultList();
        System.out.println(list.size());
        session.close();
        return  list;
    }



}
