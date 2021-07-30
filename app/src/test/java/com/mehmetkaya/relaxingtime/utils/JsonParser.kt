package com.mehmetkaya.relaxingtime.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.Reader

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    // https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#encoding-defaults
    encodeDefaults = true
}

inline fun <reified T> parseFile(
    fileName: String
): T = json.decodeFromString(readJsonFile(fileName))

fun readJsonFile(fileName: String): String {
    return json.javaClass.classLoader!!.getResourceAsStream(fileName)
        .bufferedReader()
        .use(Reader::readText)
}
