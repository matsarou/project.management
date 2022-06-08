package com.jrp.project.management.dao;

import com.jrp.project.management.entities.Employee;
import com.jrp.project.management.entities.EmployeeProject;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public abstract class EmployeeRepository2 implements EmployeeRepository {

    @Override
    public List<Employee> findAll() {
        return null;
    }

    //TODO improve it
    @Override
    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects() {
        return null;
    }
}
