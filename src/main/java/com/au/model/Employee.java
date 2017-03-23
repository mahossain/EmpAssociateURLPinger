package com.au.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mohammad
 */
public class Employee {

    private int id;
    private String name;
    private Organization organization;
    private Employee manager;
    private Collection<Project> projects = new ArrayList<>();

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        return organization != null ? organization.equals(employee.organization) : employee.organization == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }

    public Employee(final String name, final Organization organization, final Employee manager, Collection<Project> projects) {
        this.name = name;
        this.organization = organization;
        this.manager = manager;
        if (projects != null) {
            this.projects = projects;
            for(Project project: projects){
                project.addTeamMember(this);
            }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


}
