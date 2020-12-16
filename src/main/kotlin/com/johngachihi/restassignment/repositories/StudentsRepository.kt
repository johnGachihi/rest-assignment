package com.johngachihi.restassignment.repositories

import com.johngachihi.restassignment.entities.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentsRepository: JpaRepository<Student, Long>