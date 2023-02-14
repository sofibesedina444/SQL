package model;

import java.util.Objects;

public class City {
    private long city_id;
    private String city_name;

    public City(long city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return city_id == city.city_id && Objects.equals(city_name, city.city_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id, city_name);
    }

    @Override
    public String toString() {
        return city_id +
                ", name='" + city_name + '\'';
    }
}
