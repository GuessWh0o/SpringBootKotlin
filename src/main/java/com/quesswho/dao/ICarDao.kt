package com.quesswho.dao

import com.quesswho.entity.Car
import com.quesswho.exceptions.CarAlreadyExist
import com.quesswho.exceptions.CarNotFoundException

interface ICarDao {
    val cars: MutableMap<Int, Car>
    val allCars: Collection<Car>

    @Throws(CarNotFoundException::class)
    fun getCarById(id: Int): Car

    @Throws(CarNotFoundException::class)
    fun deleteCarById(id: Int)

    @Throws(CarNotFoundException::class)
    fun updateCar(car: Car): Car

    @Throws(CarAlreadyExist::class)
    fun addCar(car: Car)
}