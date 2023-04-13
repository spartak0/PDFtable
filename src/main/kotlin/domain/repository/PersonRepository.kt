package domain.repository

import domain.models.Person

interface PersonRepository {
    fun generatePerson():Person
    fun getPersonFields():List<String>
}