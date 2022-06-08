package com.jrp.project.management.dao;

import com.jrp.project.management.entities.Employee;
import com.jrp.project.management.entities.EmployeeProject;
import com.jrp.project.management.entities.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName " +
        "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " +
        "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    List<EmployeeProject> employeeProjects();
}
