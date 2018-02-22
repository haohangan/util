package demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main( String[] args ) throws IOException {

        Path p = Paths.get("D://json2.txt");
        byte[] bytes = Files.readAllBytes(p);
        String source = new String(bytes);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MyJson.class, new OwnDeserializer());
        Gson gson = gsonBuilder.create();

        MyJson natural = gson.fromJson(source, MyJson.class);
//        MyJson natural = gson.fromJson(source, new TypeToken<MyJson>(){}.getType());
        System.out.println(natural);

    }
}
