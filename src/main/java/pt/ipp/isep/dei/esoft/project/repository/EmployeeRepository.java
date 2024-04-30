package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private List<Employee> employees;

    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployeesWithSkills(List<Skill> requiredSkills) {
        List<Employee> matchingEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.hasAllSkills(requiredSkills)) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }

    public void saveEmployee(Employee employee) {
        employees.add(employee);
    }

}

class Employee {
    private String name;
    private List<Skill> skills;

    public Employee(String name) {
        this.name = name;
        this.skills = new ArrayList<>();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public boolean hasAllSkills(List<Skill> requiredSkills) {
        return skills.containsAll(requiredSkills);
    }

}

