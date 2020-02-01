package com.quesswho.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Car with this ID already exists")
public class CarAlreadyExist extends Exception {

}