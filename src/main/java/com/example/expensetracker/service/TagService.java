package com.example.expensetracker.service;


import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getTags(){
        return (List<Tag>) tagRepository.findAll();
    }

    public boolean save(Tag tag) throws DataAccessException {
        tagRepository.save(tag);
        return true;
    }
}
