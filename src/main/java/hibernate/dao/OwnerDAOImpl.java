package hibernate.dao;

import hibernate.entity.Owner;
import org.hibernate.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO{

    protected final SessionFactory sessionFactory;

    public OwnerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Owner> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Owner> cq = cb.createQuery(Owner.class);
        Root<Owner> rootEntry = cq.from(Owner.class);
        CriteriaQuery<Owner> all = cq.select(rootEntry);
        TypedQuery<Owner> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Owner findById(Long id) {
        return sessionFactory.openSession().get(Owner.class, id);
    }

    @Override
    public Long save(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long savedOwnerId = null;
             try {
                 transaction = session.beginTransaction();
                 savedOwnerId = (Long) session.save(owner);
                 transaction.commit();
             } catch (HibernateException e) {
                 if (transaction != null) {
                     transaction.rollback();
                     throw new RuntimeException(e);
                 }
             } finally {
                 session.close();
             }
        return savedOwnerId;
    }

    @Override
    public Owner update(Owner owner) {
        Owner updateOwner = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            updateOwner = (Owner) session.merge(owner);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
        return updateOwner;
    }

    @Override
    public void delete(Owner owner) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Owner load = session.load(Owner.class, owner.getId());
            session.delete(load);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
