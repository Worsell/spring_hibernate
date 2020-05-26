package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   // @SuppressWarnings("unchecked")
   public User getCarByNN(int series, String name) {
      Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();
      Query query=session.createQuery("from Car as c where c.series = :series and c.name = :name");
      query.setParameter("series", series);
      query.setParameter("name", name);
      transaction.commit();
      Car c = (Car) query.getSingleResult();
      session.close();
      return c.getOwner();
   }

}
