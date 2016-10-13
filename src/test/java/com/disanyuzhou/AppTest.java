package com.disanyuzhou;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void test()
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ls");
        Map<String, String> sysMap = processBuilder.environment();
        for (Map.Entry<String, String> kv : sysMap.entrySet())
        {
//            println(kv.getKey() + "\t" + kv.getValue());
        }

        try {
            println(processBuilder.redirectErrorStream());
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String read = bufferedReader.readLine();
            while (read != null)
            {
                println(read);
                read = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public void test2()
    {

        Jedis jedis = new Jedis("localhost", 6379);
        println(jedis.get("name"));


    }

































    public void println(Object object)
    {
        System.out.println(object);
    }
}
