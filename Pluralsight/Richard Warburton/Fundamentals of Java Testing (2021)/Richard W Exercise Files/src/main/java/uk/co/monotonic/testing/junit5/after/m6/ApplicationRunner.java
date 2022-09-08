package uk.co.monotonic.testing.junit5.after.m6;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ApplicationRunner
{
    public String run(String... arguments)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(byteStream);
        SalesReportRunner.run(arguments, output);
        try
        {
            return byteStream.toString("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return "";
        }
    }
}
