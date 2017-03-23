package com.au.service;

import com.au.model.Employee;
import com.au.model.Organization;
import com.au.model.Project;

import java.util.Set;

/**
 * Created by Mohammad on 23/3/17.
 */
public interface EmployeeService {
    Set<Employee> findTeammates(final Organization organization, final Employee employee);
    Employee findCommonManager(final Organization organization, final Employee employee1, final Employee employee2);
    Employee findClosestPeopleManager(final Organization organization, final Project project);
}
