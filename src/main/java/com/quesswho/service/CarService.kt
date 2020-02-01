package com.quesswho.service

import com.quesswho.dao.CarDao
import com.quesswho.entity.Car
import com.quesswho.exceptions.CarAlreadyExist
import com.quesswho.exceptions.CarNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CarService {

    @Autowired
    private lateinit var carDao: CarDao

    val allCars: Collection<Car>
        get() = carDao.allCars


    @Throws(CarNotFoundException::class)
    fun getCarById(id: Int): Car {
        return carDao.getCarById(id)
    }

    @Throws(CarNotFoundException::class)
    fun deleteCarById(id: Int) {
        carDao.deleteCarById(id)
    }

    @Throws(CarNotFoundException::class)
    fun updateCar(car: Car) = carDao.updateCar(car)

    @Throws(CarAlreadyExist::class)
    fun addCar(car: Car) = carDao.addCar(car)
}