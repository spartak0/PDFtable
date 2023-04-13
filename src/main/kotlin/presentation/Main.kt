package presentation

import data.repository.PersonRepositoryImpl
import data.services.PersonGenerator
import domain.mapper.PersonMapper
import domain.repository.PersonRepository
import utils.PdfHandler
import utils.PersonHandler


fun main() {
    val personRepository = initPersonRepository()
    val pdfHandler = PdfHandler()
    val personHandler = PersonHandler()
    val personList = personHandler.createPersonList(30, personRepository)
    val parsedPersonList = personHandler.parsePersonList(personList)
    pdfHandler.createPdfTable(
        "result.pdf",personRepository.getPersonFields(), parsedPersonList)
}

fun initPersonRepository(): PersonRepository {
    val personMapper = PersonMapper()
    val personGenerator = PersonGenerator()
    return PersonRepositoryImpl(personMapper, personGenerator)
}