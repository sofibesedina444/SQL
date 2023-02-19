package dao.impl;

import dao.CityDao;
import dao.EmployeeDao;
import hibernate.HibernateSessionFactoryUtil;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    private final CityDao cityDao = new CityDaoImpl();

    @Override
    public List<Employee> findAll() {
        List<Employee> employees;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = new ArrayList<>(session.createQuery("From Employee", Employee.class).list());
            transaction.commit();
        }
        return employees;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employee.getCity() != null && cityDao.getCity(employee.getCity().getCityId()).isEmpty()) {
            employee.setCity(null);
        }
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(employee);
            Employee createdEmployee = session.get(Employee.class, createdId);
            transaction.commit();
            return createdEmployee;
        }
    }

    @Override
    public Optional<Employee> getEmployee(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Employee.class, id));
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getCity() != null && cityDao.getCity(employee.getCity().getCityId()).isEmpty()) {
            employee.setCity(null);
        }
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Employee updatedEmployee = entityManager.merge(employee);
        entityTransaction.commit();
        return updatedEmployee;
    }

    @Override
    public Optional<Employee> deleteEmployee(Employee employee) {
        Optional<Employee> employeeOptional = getEmployee(employee.getId());
        if (employeeOptional.isPresent()) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(employeeOptional.get());
                transaction.commit();
                return employeeOptional;
            }
        }
        return Optional.empty();
    }
}
