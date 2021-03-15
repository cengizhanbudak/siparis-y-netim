package com.uniyaz.core.dao;

import com.uniyaz.core.domain.Musteri;
import com.uniyaz.core.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MusteriDao {
    public void saveMusteri(Musteri musteri) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(musteri);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<Musteri> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     musteriAlias " +
                            "From       Musteri musteriAlias ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteMusteri(Musteri musteri){
        SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(musteri);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
