package com.johngachihi.restassignment.exceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

class StudentNotFoundException(private val id: Long) :
    RuntimeException("Could not find student $id")