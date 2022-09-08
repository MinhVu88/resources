package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dispatcher implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	private KafkaProducer<Integer, String> kafkaProducer;
	private String topic;
	private String fileLocation;

	public Dispatcher(
		KafkaProducer<Integer, String> kafkaProducer,
		String topic,
		String fileLocation
	) {
		this.kafkaProducer = kafkaProducer;
		this.topic = topic;
		this.fileLocation = fileLocation;
	}

	@Override
	public void run() {
		logger.info("start processing " + fileLocation);

		File file = new File(fileLocation);

		int lines = 0;

		try(
			Scanner scanner = new Scanner(file)
		) {
			while(scanner.hasNextLine()) {
				String dataLine = scanner.nextLine();

				kafkaProducer.send(new ProducerRecord<>(topic, null, dataLine));

				lines++;
			}

			logger.info(
			"done sending " + lines +
				" messages from " + fileLocation +
				" to kafka topic " + topic
			);
		}catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
