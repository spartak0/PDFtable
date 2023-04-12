fun main() {
    val service = PersonGenerator()
    repeat(10) {
        println(service.generatePerson())
    }
}