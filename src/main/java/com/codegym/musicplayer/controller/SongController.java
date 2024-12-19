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

    @Value("${file-upload}")
    private String fileUpload;
    @PostMapping("/save")
    public ModelAndView save(SongForm songForm) {
        MultipartFile multipartFile = songForm.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(songForm.getLink().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Song song = new Song(songForm.getId(), songForm.getTitle(), songForm.getArtist(), songForm.getGenre(), fileName);
        songService.save(song);
        return modelAndView;
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
