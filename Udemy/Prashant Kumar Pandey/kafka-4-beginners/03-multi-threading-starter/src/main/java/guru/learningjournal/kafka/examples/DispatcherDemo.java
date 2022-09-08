package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DispatcherDemo {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		Properties properties = new Properties();

		try(
			InputStream inputStream = Files.newInputStream(Paths.get(AppConfigs.kafkaConfigFileLocation))
		) {
			properties.load(inputStream);

			properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
			properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
			properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

			try(
				KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties)
			) {
				int numberOfFiles = AppConfigs.eventFiles.length;

				Thread[] dispatchers = new Thread[numberOfFiles];

				logger.info("starting dispatcher threads");

				for(int i = 0; i < numberOfFiles; i++) {
					dispatchers[i] = new Thread(
						new Dispatcher(
							kafkaProducer,
							AppConfigs.topicName,
							AppConfigs.eventFiles[i]
						)
					);

					dispatchers[i].start();
				}

				for(Thread dispatcher : dispatchers) {
					dispatcher.join();
				}
			}catch(InterruptedException e) {
				logger.info("main thread interrupted");
			}
		}catch(IOException e) {
			throw new RuntimeException(e);
		}finally {
			logger.info("DispatcherDemo is done");
		}
	}
}
