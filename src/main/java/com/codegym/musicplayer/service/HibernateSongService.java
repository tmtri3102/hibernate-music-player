package com.codegym.musicplayer.service;

import com.codegym.musicplayer.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateSongService implements ISongService {
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
