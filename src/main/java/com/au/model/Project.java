package com.au.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mohammad
 */
public class Project {

    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    private Set<Employee> team = new HashSet<>();

    public Project() {
    }

    public Project(final String name){
        this.name = name;
    }
    
    public Project(final String name, final Set<Employee> team){
        this.name = name;
        this.team = team;
    }

    public void addTeamMember(Employee employee){
        team.add(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return name != null ? name.equals(project.name) : project.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Employee> getTeam() {
        return team;
    }

    public void setTeam(Set<Employee> team) {
        this.team = team;
    }
}
