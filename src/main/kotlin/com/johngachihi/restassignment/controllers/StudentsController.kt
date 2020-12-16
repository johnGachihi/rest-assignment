package com.johngachihi.restassignment.controllers

import com.johngachihi.restassignment.controllers.hateoas_assemblers.StudentModelAssembler
import com.johngachihi.restassignment.entities.Student
import com.johngachihi.restassignment.exceptions.StudentNotFoundException
import com.johngachihi.restassignment.repositories.StudentsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.web.bind.annotation.*

@RestController
class StudentsController {
    @Autowired
    lateinit var studentsRepository: StudentsRepository
    @Autowired
    lateinit var studentAssembler: StudentModelAssembler

    @GetMapping("/students/{id}")
    @ResponseBody
    fun one(@PathVariable id: Long): EntityModel<Student> {
        val student = studentsRepository.findById(id)
            .orElseThrow {
                StudentNotFoundException(id)
            }

        return studentAssembler.toModel(student)
    }

    @GetMapping("/students")
    @ResponseBody
    fun all(): CollectionModel<EntityModel<Student>> {
        val students = studentsRepository.findAll().map {
            studentAssembler.toModel(it)
        }

        return CollectionModel.of(students,
            linkTo<StudentsController> { all() }.withSelfRel()
        )
    }

    @PutMapping("/students")
    @ResponseBody
    fun add(@RequestBody student: Student): Student {
        return studentsRepository.save(student)
    }

    @PostMapping("/students")
    @ResponseBody
    fun update(@RequestBody student: Student): Student {
        val oldStudent = studentsRepository.findById(student.id)
            .orElseThrow {
                StudentNotFoundException(student.id)
            }

        oldStudent.apply {
            firstName = student.firstName
            secondName = student.secondName
            email = student.email
            address = student.address
            phoneNumber = student.phoneNumber
            entryPoints = student.entryPoints
        }

        return studentsRepository.save(oldStudent)
    }

    @DeleteMapping("/students/{id}")
    fun delete(@PathVariable id: Long): Map<String, String> {
        try {
            studentsRepository.deleteById(id)
            return mapOf("message" to "success")
        } catch (e: EmptyResultDataAccessException) {
            throw StudentNotFoundException(id)
        }
    }
}