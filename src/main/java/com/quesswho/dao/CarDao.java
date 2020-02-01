package com.quesswho.dao;

import com.quesswho.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CarDao {

    private static Map<Integer, Car> cars;

    static {
        cars = new HashMap<Integer, Car>() {
            {
                put(1, new Car(1, "BMW", 130));
                put(2, new Car(2, "Mercedes Benz", 250));
                put(3, new Car(3, "Opel", 140));
            }
        };
    }

    public Collection<Car> getAllCars() {
        return cars.values();
    }
}
