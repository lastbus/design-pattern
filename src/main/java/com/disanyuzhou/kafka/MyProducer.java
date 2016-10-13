package com.disanyuzhou.kafka;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by make on 7/2/16.
 */
public class MyProducer {

    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.100:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        long time = System.currentTimeMillis();

        Producer<String, String> producer = new KafkaProducer(props);
        for(long i = 0; i < 10000; i++)
            producer.send(new ProducerRecord<String, String>("test", Long.toString(i),Long.toString(i + time)), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(recordMetadata!=null)System.out.println(recordMetadata);
                    if (e != null) System.out.println(e.getMessage());
                }
            });

        producer.close();




    }
}
