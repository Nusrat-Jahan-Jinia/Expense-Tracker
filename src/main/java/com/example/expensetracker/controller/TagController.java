package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.repository.TagRepository;
import com.example.expensetracker.service.TagService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final TagRepository tagRepository;

    public TagController(TagService tagService, TagRepository tagRepository){
        this.tagService = tagService;
        this.tagRepository = tagRepository;
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
    public String addTag(@Valid Tag tag, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tag/create";
        }
        tagRepository.save(tag);
        return "redirect:/tags";
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

    @PostMapping("/edit/{id}")
    public String updateTag(@Valid @ModelAttribute("tag") Tag tag, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tag/edit";
        }
        tagRepository.save(tag);
        return "redirect:/tags";
    }
}
