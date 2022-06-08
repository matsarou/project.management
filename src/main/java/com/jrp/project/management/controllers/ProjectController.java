package com.jrp.project.management.controllers;

import com.jrp.project.management.dao.ProjectRepository;
import com.jrp.project.management.entities.Employee;
import com.jrp.project.management.entities.Project;
import com.jrp.project.management.services.EmployeeService;
import com.jrp.project.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project aProject = new Project();
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("project", aProject); //Bind the html form to a Project object
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
//    public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
    public String createProject(Project project, Model model) {
        projectService.save(project);
//        Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//        chosenEmployees.forEach(e -> {e.setProjects(); empRepo.save(e);});
        return "redirect:/projects/new"; //Use redirect to prevent duplicate submissions
    }
}
