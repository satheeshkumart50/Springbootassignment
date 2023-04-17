package notificationservice1;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/*
 * Consumer Class receives message from the Kafka messsage topic
 */
public class Consumer {
	
	// Kafka Server name
	private static String bootstrapServer = "ec2-34-200-166-55.compute-1.amazonaws.com:9092";
	// Kafka topic name
	private static String kafkaTopic = "message";
	
	public static void main (String[] args) {
		Properties props = new Properties();
        props.setProperty("bootstrap.servers", bootstrapServer);
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(kafkaTopic));
        
        Set<String> subscribedTopics = consumer.subscription();
        subscribedTopics.stream().forEach(System.out::println);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        } finally {
            consumer.close();
        }
	}

}
