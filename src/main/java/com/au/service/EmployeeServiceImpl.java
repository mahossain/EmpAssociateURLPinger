package com.au.service;

import com.au.model.Employee;
import com.au.model.Organization;
import com.au.model.Project;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad
 */
public class EmployeeServiceImpl implements EmployeeService {

    private volatile static EmployeeServiceImpl instance = null;

    private EmployeeServiceImpl() {
    }

    public static EmployeeService getInstance() {
        if (instance == null) {
            synchronized (EmployeeServiceImpl.class) {
                if (instance == null) {
                    instance = new EmployeeServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Set<Employee> findTeammates(final Organization organization, final Employee employee) {
        Set<Employee> teammates = new HashSet<>();
        if (employee.getOrganization().getName().equals(organization.getName())) {
            Collection<Project> projects = employee.getProjects();

            for (Project project : projects) {
                teammates.addAll(project.getTeam());
            }
        }
        teammates.remove(employee);
        return teammates;
    }

    @Override
    public Employee findCommonManager(final Organization organization, final Employee employee1, final Employee employee2) {
        Set<Employee> commonManagers = new HashSet<>();
        if (employee1.getOrganization().getName().equals(employee2.getOrganization().getName())) {
            commonManagers.addAll(allManagers(employee1));
            commonManagers.retainAll(allManagers(employee2));
        }
        if (!commonManagers.isEmpty()) {
            return commonManagers.iterator().next();
        }
        return null;
    }

    @Override
    public Employee findClosestPeopleManager(final Organization organization, final Project project) {

        Map<Employee, Integer> managerToEmployee = new HashMap<>();

        for (Employee teamMember : project.getTeam()) {
            Employee manager = teamMember.getManager();
            Integer numOfEmp = managerToEmployee.get(manager);
            if (numOfEmp == null) {
                managerToEmployee.put(manager, 1);
            } else {
                managerToEmployee.put(manager, numOfEmp.intValue() + 1);
            }
        }

        final Employee manager = Collections.max(managerToEmployee.entrySet(), Map.Entry.comparingByValue()).getKey();
        return manager;
    }

    private Set<Employee> allManagers(final Employee employee) {
        Set<Employee> managers = new HashSet<>();
        Employee manager = employee.getManager();
        while (manager != null) {
            managers.add(manager);
            manager = manager.getManager();
        }
        return managers;
    }
}
