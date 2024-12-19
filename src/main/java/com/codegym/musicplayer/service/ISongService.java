package com.codegym.musicplayer.service;

import com.codegym.musicplayer.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(int id);
    void save(Song song);
    void delete(int id);
    void update(Song song);
    List<Song> findByTitle(String title);
}
