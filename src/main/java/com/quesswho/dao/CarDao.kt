package com.quesswho.dao

import com.quesswho.entity.Car
import com.quesswho.exceptions.CarAlreadyExist
import com.quesswho.exceptions.CarNotFoundException
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

@Repository
class CarDao : ICarDao {
    override val cars: MutableMap<Int, Car> = mutableMapOf(
            1 to Car(1, "BMW", 130),
            2 to Car(2, "Mercedes Benz", 250),
            3 to Car(3, "Opel", 140))


    override val allCars: Collection<Car>
        get() = cars.values

    @Throws(CarNotFoundException::class)
    override fun getCarById(id: Int): Car {
        cars[id].let {
            if (it == null) {
                throw CarNotFoundException()
            } else {
                return it
            }
        }
    }

    @Throws(CarNotFoundException::class)
    override fun deleteCarById(id: Int) {
        if (cars.containsKey(id)) {
            cars.remove(id)
        } else {
            throw CarNotFoundException()
        }
    }

    @Throws(CarNotFoundException::class)
    override fun updateCar(car: Car): Car {
        try {
            if (cars.replace(car.id, car) == null) {
                throw CarNotFoundException()
            }
            return car
        } catch (e: Exception) {
            throw CarNotFoundException()
        }
    }

    @Throws(CarAlreadyExist::class)
    override fun addCar(car: Car) {
        if (cars.containsKey(car.id)) {
            throw CarAlreadyExist()
        } else {
            cars[car.id] = car
        }
    }
}