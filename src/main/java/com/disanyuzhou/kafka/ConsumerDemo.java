package com.disanyuzhou.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * Created by make on 6/3/16.
 */
public class ConsumerDemo {

    public static void main(String args[])
    {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "test");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        KafkaConsumer consumer = new KafkaConsumer(properties);
        consumer.subscribe(Arrays.asList("test"));

        while (true){
            ConsumerRecords record = consumer.poll(100L);
            record.partitions();
            System.out.println(record.count());
            System.out.println("========");

        }

    }
}
