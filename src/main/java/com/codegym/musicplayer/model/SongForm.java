package com.codegym.musicplayer.model;

import org.springframework.web.multipart.MultipartFile;

public class SongForm {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private MultipartFile link;

    public SongForm(int id, String title, String artist, String genre, MultipartFile link) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.link = link;
    }

    public SongForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MultipartFile getLink() {
        return link;
    }

    public void setLink(MultipartFile link) {
        this.link = link;
    }
}
