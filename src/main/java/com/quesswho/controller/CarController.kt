package com.quesswho.controller

import com.quesswho.entity.Car
import com.quesswho.exceptions.CarAlreadyExist
import com.quesswho.exceptions.CarNotFoundException
import com.quesswho.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/cars")
class CarController {

    @Autowired
    private lateinit var carService: CarService

    @get:RequestMapping(method = [RequestMethod.GET])
    val allCars: Collection<Car>
        get() = carService.allCars

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun getCarById(@PathVariable("id") id: Int): Car {
        try {
            return carService.getCarById(id)
        } catch (ex: CarNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "The car does not exist", ex)
        }
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteCarById(@PathVariable("id") id: Int) : ResponseEntity<String> {
        try {
            carService.deleteCarById(id)
            return ResponseEntity.status(
                    HttpStatus.OK
            ).body("The car with id=$id has been successfully deleted")
        } catch (ex: CarNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "The car can not be deleted as it does not exist", ex)
        }
    }

    @RequestMapping(method = [RequestMethod.PUT], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCar(@RequestBody car: Car): Car {
        try {
            return carService.updateCar(car)
        } catch (ex: CarNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "The car does not exist", ex)
        }
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addCar(@RequestBody car: Car): ResponseEntity<String> {
        try {
            carService.addCar(car)
            return ResponseEntity.status(HttpStatus.OK).body("The car was successfully added")
        } catch (ex: CarAlreadyExist) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "The car with this ID already exists", ex)
        }
    }
}