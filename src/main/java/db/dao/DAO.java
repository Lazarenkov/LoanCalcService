package db.dao;

import db.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;


public class DAO<T> {

    Session session;
    CriteriaBuilder criteriaBuilder;

    private void init() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        criteriaBuilder = session.getCriteriaBuilder();
    }

    public void executeNativeQuery(String sql) {
        init();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    public UUID save(T obj) {
        init();
        Transaction transaction = session.beginTransaction();
        UUID id = (UUID) session.save(obj);
        transaction.commit();
        session.close();
        return id;
    }

    public void delete(T obj) {
        init();
        Transaction transaction = session.beginTransaction();
        session.remove(obj);
        transaction.commit();
        session.close();
    }

    public T retrieveById(UUID id, Class<T> c) {
        init();
        return session.get(c, id);
    }

    public List<T> retrieveAll(Class<T> c) {
        init();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root);

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<T> retrieveWhere(Class<T> c, String column, int value) {
        init();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(column), value));

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<T> retrieveWhere(Class<T> c, String column, double value) {
        init();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(column), value));

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<T> retrieveWhere(Class<T> c, String column, boolean value) {
        init();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(column), value));

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<T> retrieveWhere(Class<T> c, String column, String value) {
        init();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
        Root<T> root = criteriaQuery.from(c);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(column), value));

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
