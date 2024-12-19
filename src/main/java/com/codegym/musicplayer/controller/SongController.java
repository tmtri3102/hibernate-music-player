package com.codegym.musicplayer.controller;

import com.codegym.musicplayer.model.Song;
import com.codegym.musicplayer.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping("")
    public String getAllSongs(Model model) {
        List<Song> songList = songService.findAll();
        model.addAttribute("songs", songList);
        return "/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        String message = "A new song has been created.";
        model.addAttribute("message", message);
        return "/create";
    }

    @PostMapping("/save")
    public String save(Song song) {
        songService.save(song);
        return "redirect:/songs/create";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "/edit";
    }

    @PostMapping("/update")
    public String update(Song song) {
        songService.update(song);
        return "redirect:/songs";
    }

    @GetMapping("/{id}/view")
    public String showViewForm(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "/view";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        songService.delete(id);
        return "redirect:/songs";
    }
//    con postmapping delete
//    check search
    @GetMapping("/search")
    public String search(@PathVariable String title, Model model) {
        List<Song> songList = songService.findByTitle(title);
        model.addAttribute("songs", songList);
        return "/index";
    }

}
