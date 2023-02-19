package dao.impl;

import dao.CityDao;
import hibernate.HibernateSessionFactoryUtil;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDao {

    @Override
    public List<City> findAll() {
        List<City> cities;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            cities = new ArrayList<>(session.createQuery("From City", City.class).list());
            transaction.commit();
        }
        return cities;
    }

    @Override
    public Optional<City> getCity(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        }
    }

    @Override
    public City addCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(city);
            City createdCity = session.get(City.class, createdId);
            transaction.commit();
            return createdCity;
        }
    }

    @Override
    public City updateCity(City city) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        City updatedCity = entityManager.merge(city);
        entityTransaction.commit();
        return updatedCity;
    }

    @Override
    public Optional<City> deleteCity(City city) {
        Optional<City> cityOptional = getCity(city.getCityId());
        if (cityOptional.isPresent()) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(cityOptional.get());
                transaction.commit();
                return cityOptional;
            }
        }
        return Optional.empty();
    }
}
