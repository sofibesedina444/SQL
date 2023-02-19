package dao;

import model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    List<City> findAll();

    Optional<City> getCity(long id);

    City addCity(City city);

    City updateCity(City city);

    Optional<City> deleteCity(City city);
}
