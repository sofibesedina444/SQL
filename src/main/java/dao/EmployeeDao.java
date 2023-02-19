package dao;

import model.City;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee addEmployee(Employee employee);

    Optional<Employee> getEmployee(long id);

    Employee updateEmployee(Employee employee);

    Optional<Employee> deleteEmployee(Employee employee);
}
