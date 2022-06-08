package com.jrp.project.management.controllers;

import com.jrp.project.management.dao.EmployeeRepository;
import com.jrp.project.management.dao.ProjectRepository;
import com.jrp.project.management.entities.Employee;
import com.jrp.project.management.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository emplRepo;

    @GetMapping("/")
    public String displayHome(Model model) { //returns a View

        //Query database for projects
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);

        //Query database for employees
        List<Employee> employees = emplRepo.findAll();
        model.addAttribute("employeesList", employees);
        return "main/home";
    }
}
