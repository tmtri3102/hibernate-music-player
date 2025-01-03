package com.codegym.musicplayer.controller;

import com.codegym.musicplayer.model.Song;
import com.codegym.musicplayer.model.SongForm;
import com.codegym.musicplayer.model.Upload;
import com.codegym.musicplayer.service.ISongService;
import com.codegym.musicplayer.service.UploadFileService;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("song")
    public Song song(){
        return new Song();
    }

    // upload form
    @RequestMapping("demo")
    public ModelAndView demo(){
        ModelAndView mav = new ModelAndView("/demo");
        String name = "Codegym";
        mav.addObject("name", name);
        List<Song> songs = songService.findAll();
        mav.addObject("songs", songs);
        mav.addObject("upload", new Upload()); // Bind Upload object to the form
        return mav;
    }
    // submit upload
    @PostMapping("upload")
    public ModelAndView upload(@ModelAttribute("upload") Upload upload) throws IOException {
        ModelAndView mav = new ModelAndView("/view2");
        System.out.println(upload.getFile());
        System.out.println("Hello World");
        UploadFileService uploadFileService = new UploadFileService();
        uploadFileService.uploadFile(upload.getFile());
        return mav;
    }





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
        System.out.println("saving " + songForm);
        System.out.println("hello");
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
        model.addAttribute("success", "Added a new song");
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
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        songService.delete(id);
        return "redirect:/songs";
    }
}
