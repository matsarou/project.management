package com.jrp.project.management.services;

import com.jrp.project.management.dao.ProjectRepository;
import com.jrp.project.management.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projRepo;

    public Project save(Project project) {
        return projRepo.save(project);
    }

    public List<Project> getAll() {
        return projRepo.findAll();
    }

//    public List<ChartData> getProjectStatus() {
//        return projRepo.getProjectStatus();
//    }
}
