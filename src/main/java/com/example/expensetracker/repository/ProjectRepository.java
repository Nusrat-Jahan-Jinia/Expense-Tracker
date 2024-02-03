package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
