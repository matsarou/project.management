package com.jrp.project.management.dao;

import com.jrp.project.management.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    public List<Project> findAll();
}
