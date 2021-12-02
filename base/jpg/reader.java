package org.example;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;

/**
 * Created by guihao on 2021-11-29 15:32
 **/
public class JPGReader {

    public static void main(String[] args) throws ImageProcessingException, IOException {
        String path = "D:\\out\\身份证.jpg";
        File jpegFile = new File(path);
        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }
}

//<dependency>
//<groupId>com.drewnoakes</groupId>
//<artifactId>metadata-extractor</artifactId>
//<version>2.16.0</version>
//</dependency>
