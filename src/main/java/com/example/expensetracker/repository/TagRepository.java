package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(collectionResourceRel = "tagList", path = "tagList")
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
