package domain.mapper

import data.models.PersonEntity
import domain.models.Person
import utils.Mapper

class PersonMapper : Mapper<PersonEntity, Person> {
    override fun dataToDomain(data: PersonEntity): Person =
        Person(
            name = data.name,
            lastName = data.lastName,
            middleName = data.middleName,
            age = data.age,
            gender = data.gender,
            dob = data.dob,
            birthPlace = data.birthPlace,
            index = data.index,
            country = data.country,
            city = data.city,
            street = data.street,
            buildingNumber = data.buildingNumber,
            apartment = data.apartment,
        )

    override fun domainToData(domain: Person): PersonEntity =
        PersonEntity(
            name = domain.name,
            lastName = domain.lastName,
            middleName = domain.middleName,
            age = domain.age,
            gender = domain.gender,
            dob = domain.dob,
            birthPlace = domain.birthPlace,
            index = domain.index,
            country = domain.country,
            city = domain.city,
            street = domain.street,
            buildingNumber = domain.buildingNumber,
            apartment = domain.apartment,
        )
}