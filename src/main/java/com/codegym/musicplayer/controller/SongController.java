package com.codegym.musicplayer.controller;

import com.codegym.musicplayer.model.Song;
import com.codegym.musicplayer.model.SongForm;
import com.codegym.musicplayer.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping
    public String getAllSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("songForm", new SongForm());
        return "/create";
    }

    @Value("${file-upload}")
    private String folderPath;

    @PostMapping("/save")
    public String save(SongForm songForm, Model model) {
        MultipartFile multipartFile = songForm.getLink();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(songForm.getLink().getBytes(), new File(folderPath + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        Song song = new Song(0, songForm.getTitle(), songForm.getArtist(), songForm.getGenre(), fileName);
        songService.save(song);
        model.addAttribute("songForm", songForm);
        return "/create";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "/update";
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
    public String showDeleteForm(@PathVariable int id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(@PathVariable int id) {
        songService.delete(id);
        return "redirect:/songs";
    }
}
