package com.johngachihi.restassignment.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    var firstName: String,
    var secondName: String,
    var email: String,
    var phoneNumber: String,
    var address: String,
    var entryPoints: Int
)