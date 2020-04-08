package me.antonyderham;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class App {
    private final static String QUEUE_NAME = "vgdata";

    public static void main(String[] args) throws IOException, TimeoutException {
        var factory = new ConnectionFactory();
        factory.setHost("localhost");
        var connection = factory.newConnection();
        var channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicConsume(QUEUE_NAME, true, App::handleMessage, consumerTag -> {
        });
    }

    private static void handleMessage(String consumerTag, Delivery delivery) throws IOException {
        var message = new String(delivery.getBody(), StandardCharsets.UTF_8);

        var records = Arrays.asList(new ObjectMapper().readValue(message, GameSalesRecord[].class));

        var totalSalesPerGenre = records.stream().collect(Collectors.groupingBy(
                GameSalesRecord::getGenre, Collectors.summingDouble(GameSalesRecord::getGlobalSales)));
        var numberTitlesPerGenre = records.stream().collect(Collectors.groupingBy(
                GameSalesRecord::getGenre, Collectors.counting()));

        System.out.println(totalSalesPerGenre);
        System.out.println(numberTitlesPerGenre);
    }
}
