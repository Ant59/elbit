package me.antonyderham

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.rabbitmq.client.ConnectionFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeoutException

const val QUEUE_NAME = "vgdata"

@Throws(IOException::class, TimeoutException::class)
fun main() {
    val json = getJsonBytes(File("src/main/resources/vgsales.csv"))
    val factory = ConnectionFactory()
    factory.host = "localhost"
    factory.newConnection().use { connection ->
        connection.createChannel().use {
            it.queueDeclare(QUEUE_NAME, false, false, false, null)
            it.basicPublish("", QUEUE_NAME, null, json)
            println("Sent Message")
        }
    }
}

@Throws(IOException::class)
private fun getJsonBytes(csv: File): ByteArray {
    val orderLineSchema = CsvSchema.emptySchema().withHeader()
    val records = CsvMapper()
            .readerFor(GameSalesRecord::class.java)
            .with(orderLineSchema)
            .readValues<GameSalesRecord>(csv)

    return ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .writeValueAsBytes(records.readAll())
}
