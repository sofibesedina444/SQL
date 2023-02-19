import dao.CityDao;
import dao.EmployeeDao;
import dao.impl.CityDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CityDao cityDao = new CityDaoImpl();

        int n = 2;
        City vladivostok = new City("Владивосток");
        List<Employee> employees = new ArrayList<>(2);
        for (int i = 0; i < n; i++) {
            employees.add(new Employee("name" + (i + 1), "last name" + (i + 1),
                    "female", 25 + i, vladivostok));
        }
        vladivostok.setEmployees(employees);
        cityDao.addCity(vladivostok);
        employeeDao.findAll().forEach(System.out::println);

        City ekaterinburg = new City("Екатеринбург");
        cityDao.addCity(ekaterinburg);

        Optional<Employee> darwin = employeeDao.getEmployee(25);
        darwin.get().setCity(ekaterinburg);
        employeeDao.updateEmployee(darwin.get());

        vladivostok.setCityName("Казань");
        cityDao.updateCity(vladivostok);
        employeeDao.findAll().forEach(System.out::println);

        Optional<City> kazan = cityDao.getCity(9);
        cityDao.deleteCity(kazan.get());
        employeeDao.findAll().forEach(System.out::println);
    }
}


