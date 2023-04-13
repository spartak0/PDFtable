package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class YamlReader {
    private val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

    fun getYamlFromRes(fileName:String): Map<*, *> {
        val yml = javaClass.classLoader.getResource(fileName)
        return mapper.readValue(yml, Map::class.java)
    }

}