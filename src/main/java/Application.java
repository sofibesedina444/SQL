import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        City city_id = new City(5, "Москва");
        Employee employee = new Employee("Carl", "Jung", "male", 85, city_id);
        employeeDao.addEmployee(employee);
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        employeeDao.deleteEmployee(9);
        employeeDao.deleteEmployee(10);
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println(employeeDao.getEmployee(8));

        employeeDao.updateAge(8, 75);
        employeeDao.findAll().forEach(System.out::println);
    }
}


