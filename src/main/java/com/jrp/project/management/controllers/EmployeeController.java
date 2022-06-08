package com.jrp.project.management.controllers;

import com.jrp.project.management.entities.Employee;
import com.jrp.project.management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee aEmployee = new Employee();
        model.addAttribute("employee", aEmployee); //Bind the html form to a Project object
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model) {
        employeeService.save(employee);
        return "redirect:/employees/new"; //Use redirect to prevent duplicate submissions
    }
}
