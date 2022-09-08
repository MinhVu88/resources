package guru.learningjournal.kafka.examples;

class AppConfigs {
	final static String applicationID = "StorageDemo";
	final static String bootstrapServers = "localhost:9093,localhost:9094";
	final static String topicName = "invoice";
	final static int numEvents = 500000;
}
