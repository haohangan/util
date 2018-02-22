package demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        Path p = Paths.get("D://json.txt");
        byte[] bytes = Files.readAllBytes(p);
        String source = new String(bytes);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
        Gson gson = gsonBuilder.create();

        Object natural = gson.fromJson(source, Object.class);

        LinkedTreeMap<String,Object> map = (LinkedTreeMap<String,Object>)natural;
        map.forEach((k,v)->{
            if(v.getClass()==com.google.gson.internal.LinkedTreeMap.class){
                LinkedTreeMap<String,Object> map1 = (LinkedTreeMap<String,Object>)v;
                map1.forEach((k1,v1)->{
                    System.out.println(v1.getClass());
                    if(v1.getClass()==ArrayList.class){
                        ArrayList<LinkedTreeMap> map2 = (ArrayList<LinkedTreeMap>)v1;
                       map2.forEach(c->{
                           System.out.println(c.getClass());
                       });
                    }
                });
            }
        });
    }
}
