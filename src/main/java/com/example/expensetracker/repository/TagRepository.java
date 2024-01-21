package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
