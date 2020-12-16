package com.johngachihi.restassignment.controllers.hateoas_assemblers

import com.johngachihi.restassignment.controllers.StudentsController
import com.johngachihi.restassignment.entities.Student
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class StudentModelAssembler: RepresentationModelAssembler<Student, EntityModel<Student>> {
    override fun toModel(entity: Student): EntityModel<Student> {
        return EntityModel.of(entity,
            linkTo<StudentsController> { one(entity.id) }.withSelfRel(),
            linkTo<StudentsController> { all() }.withRel("students")
        )
    }
}