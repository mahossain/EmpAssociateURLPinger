package com.au.service;

import com.au.model.Employee;
import com.au.model.Organization;
import com.au.model.Project;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Mohammad
 *         John works for Lisa and works on Project A and Project B
 *         Jack also works for Lisa and is the works on Project A
 *         James works for Leonard and works on Project A and Project C
 *         Lucy works for Leonard and works on Project B and Project C
 *         Sam works for Leonard and works on Project B
 *         Lisa and Leonard work for Simon
 */
public class EmployeeServiceImplTest {

    Project projectA = new Project("A");
    Project projectB = new Project("B");
    Project projectC = new Project("C");

    Organization organization = new Organization("NewsCorp");
    Employee simon = new Employee("Simon", organization, null, null);

    Employee leonard = new Employee("Leonard", organization, simon, null);
    Employee lisa = new Employee("Lisa", organization, simon, null);

    //John works for Lisa and works on Project A and Project B
    Employee john = new Employee("John", organization, lisa, Arrays.asList(projectA, projectB));
    //Jack also works for Lisa and is the works on Project A
    Employee jack = new Employee("Jack", organization, lisa, Arrays.asList(projectA));

    //James works for Leonard and works on Project A and Project C
    Employee james = new Employee("James", organization, leonard, Arrays.asList(projectA, projectC));

    //Lucy works for Leonard and works on Project B and Project C
    Employee lucy = new Employee("Lucy", organization, leonard, Arrays.asList(projectB, projectC));
    //Sam works for Leonard and works on Project B
    Employee sam = new Employee("Sam", organization, leonard, Arrays.asList(projectB));

    @Before
    public void setUp() {
    }


    //Example: James is teammates with John, Jack and Lucy
    @Test
    public void testFindTeammates() {
        Organization organization = this.organization;
        Employee employee = james;
        EmployeeService instance = EmployeeServiceImpl.getInstance();
        Set<Employee> expResult = null;
        Set<Employee> result = instance.findTeammates(organization, employee);
        assertEquals(3, result.size());
    }

    @Test
    public void testFindCommonManager() {
        Organization organization = this.organization;
        Employee employee1 = john;
        Employee employee2 = jack;
        EmployeeService instance = EmployeeServiceImpl.getInstance();
        Employee expResult = lisa;
        Employee result = instance.findCommonManager(organization, employee1, employee2);
        assertEquals(expResult, result);
    }

    @Test
    public void testClosestPeopleManagerResponsibleFor() {
        Organization organization = this.organization;
        EmployeeService instance = EmployeeServiceImpl.getInstance();
        Employee manager = instance.findClosestPeopleManager(organization, projectA);
        assertNotNull(manager);
        assertEquals(manager.getName(), "Lisa");
    }
}