package dao;

import model.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    void addEmployee(Employee employee);
    Employee getEmployee(long id);
    void updateAge(Employee employee);
    void deleteEmployee(Employee employee);
    public void addOrUpdateEmployee(Employee employee);
}
