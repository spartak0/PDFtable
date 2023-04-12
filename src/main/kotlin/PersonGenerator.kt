import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig

class PersonGenerator {
    private val middleNameMap by lazy { fetchMiddleName() }
    private val maleMiddleName = middleNameMap["male_middle_name"] as List<String>
    private val femaleMiddleName = middleNameMap["female_middle_name"] as List<String>
    private val config = fakerConfig { locale = "ru" }
    private val generator = Faker(config)

    init {
        generator.unique.configuration {
            enable(generator::name)
            enable(generator::address)

        }
    }

    fun generatePerson(): Person {
        val gender = generateGender()
        val name = generateName(gender)
        val surname = generateSurname(gender)
        val middleName = generateMiddleName(gender)
        val city = generateCity()
        val age = generateAge()
        val dob = dobByAge(age)
        generator.name.nameWithMiddle()
        return Person(
            name = name,
            lastName = surname,
            middleName = middleName,
            age = age,
            gender = gender,
            dob = dob,
            city = city
        )
    }


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

        else -> ""
    }

    private fun fetchMiddleName(): Map<String, List<String>> =
        YamlReader().getYamlFromRes("middle_name.yml") as Map<String, List<String>>

}
