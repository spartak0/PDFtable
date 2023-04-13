package utils

import domain.models.Person
import domain.repository.PersonRepository

class PersonHandler {

    fun parsePersonList(list: List<Person>): List<String> {
        val parsListPerson = mutableListOf<String>()
        list.forEach { person ->
            val tmp = parsePerson(person)
            tmp.forEach {
                parsListPerson.add(it)
            }
        }
        return parsListPerson
    }

    fun parsePerson(person: Person): List<String> = listOf(
        person.name,
        person.lastName,
        person.middleName,
        person.age.toString(),
        person.gender,
        person.dob,
        person.birthPlace,
        person.index,
        person.country,
        person.city,
        person.street,
        person.buildingNumber.toString(),
        person.apartment.toString(),
    )

    fun createPersonList(size: Int, repository: PersonRepository): List<Person> =
        List(size) {
            repository.generatePerson()
        }

}