package domain.models

data class Person(
    val name: String,
    val lastName: String,
    val middleName: String,
    val age: Int,
    val gender: String,
    val dob: String,
    val birthPlace: String,
    val index: String,
    val country: String,
    val city:String,
    val street:String,
    val buildingNumber:Int,
    val apartment:Int,
) {
    companion object {
        fun getFieldsList(): List<String> {
            return listOf(
                "Имя",
                "Фамилия",
                "Отчество",
                "Возраст",
                "Пол",
                "Дата рождения",
                "Место рождения",
                "Индекс",
                "Страна",
                "Город",
                "Улица",
                "Дом",
                "Квартира",
            )
        }
    }

}