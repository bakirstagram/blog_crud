package ru.chashka.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chashka.spring.model.Post;
import ru.chashka.spring.repository.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }


    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam(value = "title") String title,
                              @RequestParam(value = "anons") String anons,
                              @RequestParam(value = "full_text") String full_text,
                              Model model) {
        // с помощью аннотаций @RequestParam мы получаем данные из формы который связан с этим action(действием)
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/blog/";
    }

    @GetMapping("/blog/{id}")
    public String blogPostDetails(@PathVariable("id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
//                  Версия Дударя
//        Optional<Post> post = postRepository.findById(id);
//        ArrayList<Post> result = new ArrayList<>();
//        post.ifPresent(result::add);

        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogUpdate(@PathVariable("id") Long id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "anons") String anons,
                             @RequestParam(value = "full_text") String full_text,
                             Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";

    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";

    }


}
