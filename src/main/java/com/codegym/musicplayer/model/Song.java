package com.codegym.musicplayer.model;

public class Song {
    private String title;
    private String artist;
    private String genre;
    private String link;

    public Song(String title, String artist, String genre, String link) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.link = link;
    }

    public Song() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
