package uk.co.monotonic.testing.junit5.after.m5_guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.PrintStream;

public class SalesModule extends AbstractModule {

    private final String fileLocation;

    public SalesModule(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    protected void configure() {
        // Which implementation should be used for an interface
        bind(SalesRepository.class).to(CsvSalesRepository.class);

        // Which object should be used to inject a class
        bind(PrintStream.class).toInstance(System.out);

        // Which object should be used for a class
        // where we might have many different values for that class (String).
        bind(String.class)
            .annotatedWith(Names.named("fileLocation"))
            .toInstance(fileLocation);
    }

}
