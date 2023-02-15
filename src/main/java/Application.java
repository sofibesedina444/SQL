import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        City city_id = new City(3, "Новгород");
        Employee employee1 = new Employee(11,"Charles", "Darwin",
                "male", 73, city_id.getCityId());
        Employee employee2 = new Employee(11,"Charles", "Darwin",
                "male", 70, city_id.getCityId());
        employeeDao.addEmployee(employee1);
        employeeDao.addOrUpdateEmployee(employee2);
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        employeeDao.deleteEmployee(employee1);
        employeeDao.deleteEmployee(employee2);
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println(employeeDao.getEmployee(8));

        employeeDao.updateAge(employee2);
        employeeDao.findAll().forEach(System.out::println);
    }
}


