package data.services

import utils.YamlReader
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import data.models.PersonEntity

class PersonGenerator {
    private val config = fakerConfig { locale = "ru" }
    private val generator = Faker(config)

    private val middleNameMap by lazy { fetchMiddleName() }
    private val maleMiddleName = middleNameMap["male_middle_name"] as List<String>
    private val femaleMiddleName = middleNameMap["female_middle_name"] as List<String>

    private val streets by lazy { fetchStreets() }

    init {
        generator.unique.configuration {
            enable(generator::name)
        }
    }


    fun generatePerson(): PersonEntity {
        val gender = generateGender()
        val name = generateName(gender)
        val surname = generateSurname(gender)
        val middleName = generateMiddleName(gender)
        val birthPlace = generateCity()
        val age = generateAge()
        val dob = dobByAge(age)
        val index = generateIndex()
        val country = generateCountry()
        val city = generateCity()
        val street = generateStreet()
        val buildingNumber = generateBuildingNumber()
        val apartment = generateApartment()
        return PersonEntity(
            name = name,
            lastName = surname,
            middleName = middleName,
            age = age,
            gender = gender,
            dob = dob,
            birthPlace = birthPlace,
            index = index,
            country = country,
            city = city,
            street = street,
            buildingNumber = buildingNumber,
            apartment = apartment
        )
    }

    private fun generateApartment() = generator.random.nextInt(1..300)

    private fun generateBuildingNumber() = generator.random.nextInt(1..100)

    private fun generateStreet():String{
        val index = generator.random.nextInt(streets.indices)
        return streets[index]
    }

    private fun generateIndex() = generator.random.nextInt(100000..999999).toString()

    private fun generateCountry() = "Россия"


    private fun generateGender(): String = generator.gender.shortBinaryTypes()
        .replace("f", "ж")
        .replace("m", "м")

    private fun generateName(gender: String) = when (gender) {
        "ж" -> generator.name.femaleFirstName()
        "м" -> generator.name.maleFirstName()
        else -> generator.name.neutralFirstName()
    }

    private fun generateSurname(gender: String) = when (gender) {
        "ж" -> generator.name.femaleLastName()
        "м" -> generator.name.maleLastName()
        else -> generator.name.lastName()
    }

    private fun generateCity() = generator.address.city()

    private fun generateAge(): Int = generator.random.nextInt(1..100)

    private fun dobByAge(age: Int): String = generator.person.birthDate(age.toLong()).toString()

    private fun generateMiddleName(gender: String): String = when (gender) {
        "ж" -> {
            val index = generator.random.nextInt(femaleMiddleName.indices)
            femaleMiddleName[index]
        }

        "м" -> {
            val index = generator.random.nextInt(maleMiddleName.indices)
            maleMiddleName[index]
        }

        else -> {
            val index = generator.random.nextInt(maleMiddleName.indices)
            maleMiddleName[index]
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun fetchMiddleName(): Map<String, List<String>> =
        YamlReader().getYamlFromRes("middle_name.yml") as Map<String, List<String>>

    @Suppress("UNCHECKED_CAST")
    private fun fetchStreets(): List<String> {
        val map = YamlReader().getYamlFromRes("streets.yml") as Map<String, List<String>>
        return map["street_title"]!!
    }


}
