package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.repository.TagRepository;
import com.example.expensetracker.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController implements WebMvcConfigurer {

    private final TagService tagService;
    private final TagRepository tagRepository;

    public TagController(TagService tagService, TagRepository tagRepository){
        this.tagService = tagService;
        this.tagRepository = tagRepository;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/tag/create/results").setViewName("tag/results");
    }
    @GetMapping(value = "")
    public String getAllTags(Model model) {
        List<Tag> tags = tagService.getTags();
        model.addAttribute("tags", tags);
        return "tag/list";
    }

    @GetMapping(value = "/create")
    public String showAddTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag/create";
    }

    @PostMapping(value = "/create")
    public String addTag(Tag tag ) {
        tagRepository.save(tag);
        return "redirect:/tag/create/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }

    @GetMapping("/edit/{id}")
    public String showEditTagForm(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tag Id:" + id));
        model.addAttribute("tag", tag);
        return "tag/edit";
    }

    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute("category") Tag tag, Model model) {
        tagRepository.save(tag);
        return "redirect:/tags/";
    }
}
