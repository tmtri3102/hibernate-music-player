package com.codegym.musicplayer.service;

import com.codegym.musicplayer.model.Song;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class HibernateSongService implements ISongService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    @Override
    public List<Song> findAll() {
        return List.of();
    }

    @Override
    public Song findById(int id) {
        return null;
    }

    @Override
    public void save(Song song) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Song song) {

    }

    @Override
    public List<Song> findByTitle(String title) {
        return List.of();
    }
}
