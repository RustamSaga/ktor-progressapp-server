package com.progress.rustsaga94.data

import com.progress.rustsaga94.data.models.Person

interface PersonDataSource {

    suspend fun putPerson(person: Person): Boolean

    suspend fun getPersonByName(personName: String): Person?

    suspend fun getAllPersons(): List<Person>

    suspend fun getPersonWithDetails(namePerson: String): List<Pair<Int, Target>>

}