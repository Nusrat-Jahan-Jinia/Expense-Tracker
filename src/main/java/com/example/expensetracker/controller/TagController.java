package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", tagService.getTags());

//        System.out.println("here" + tagService.getTags());
        modelAndView.setViewName("tag/list.html");
        return modelAndView;
    }
    @GetMapping(value = "/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Tag());
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("tag/create-edit.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(@ModelAttribute("dto") Tag tag, Model model){
        boolean success = tagService.save(tag);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/tags";
    }
}
