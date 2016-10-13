package com.disanyuzhou;

import java.util.Map;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Map<String, String> props = System.getenv();
        for (Map.Entry<String, String> item : props.entrySet())
        {
            System.out.println(item.getKey() + "\t" + item.getValue());
        }

        System.out.println(props.containsKey("user.dir"));
        System.out.println(props.get("PATH"));

    }
}
