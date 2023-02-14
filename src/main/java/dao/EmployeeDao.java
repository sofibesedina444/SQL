package dao;

import model.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    void addEmployee(Employee employee);
    Employee getEmployee(long id);
    void updateAge(long id, int age);
    void deleteEmployee(long id);
}
