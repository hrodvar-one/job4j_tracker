package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            return item;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean replace(Integer id, Item item) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            int updatedRows = session.createQuery(
                    "update Item i set i.name = :name where i.id = :id")
                    .setParameter("name", item.getName())
                    .setParameter("id", id)
                    .executeUpdate();

            tx.commit();
            return updatedRows > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            int deletedRows = session.createQuery(
                    "delete from Item i where i.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            tx.commit();
            return deletedRows > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Item> query = session.createQuery("from Item", Item.class);
            List<Item> items = query.list();
            tx.commit();
            return items;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query<Item> query = session.createQuery("FROM Item i WHERE i.name = :name", Item.class);
            query.setParameter("name", key);
            List<Item> items = query.list();

            tx.commit();

            return items;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Item findById(Integer id) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query<Item> query = session.createQuery("FROM Item i WHERE i.id = :id", Item.class);
            query.setParameter("id", id);

            Item item = query.uniqueResult();
            tx.commit();

            return item;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
