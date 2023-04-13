package data.repository

import data.services.PersonGenerator
import domain.mapper.PersonMapper
import domain.models.Person
import domain.repository.PersonRepository

class PersonRepositoryImpl(
    private val personMapper: PersonMapper,
    private val personGenerator: PersonGenerator
) : PersonRepository {
    override fun generatePerson(): Person = personGenerator.generatePerson().let { personMapper.dataToDomain(it) }
    override fun getPersonFields(): List<String> = Person.getFieldsList()
}