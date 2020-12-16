package com.johngachihi.restassignment.controllers.advice

import com.johngachihi.restassignment.exceptions.StudentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class StudentNotFoundAdvice {

    data class Response(val message: String?)

    @ResponseBody
    @ExceptionHandler(StudentNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun studentNotFoundHandler(ex: StudentNotFoundException) = Response(ex.message)
}