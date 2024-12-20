package com.codegym.musicplayer.service;

import com.codegym.musicplayer.model.Song;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateSongService implements ISongService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }




    @Override
    public List<Song> findAll() {
        String queryStr = "FROM Song";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        return query.getResultList();
    }

    @Override
    public Song findById(int id) {
        String queryStr = "FROM Song WHERE id = :id";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Song song) {
        update(song);
    }

    @Override
    public void delete(int id) {
        Song song = findById(id);
        if (song != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(song);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

    }

    @Override
    public void update(Song song) {
        Transaction transaction = null;
        Song origin;
        if (song.getId() == 0) {
            origin = new Song();
        } else {
            origin = findById(song.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setTitle(song.getTitle());
            origin.setArtist(song.getArtist());
            origin.setGenre(song.getGenre());
            origin.setLink(song.getLink());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


}
