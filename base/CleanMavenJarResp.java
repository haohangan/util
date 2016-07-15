package algorithm.clean;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class CleanMavenJarResp {
	
	 public static final String path ="/home/m2/";
    public static void main(String[] args) {
		Iterator<File> fs = FileUtils.iterateFiles(new File(path), new String[]{"lastUpdated"}, true);
		while(fs.hasNext()){
			File f = fs.next();
			System.out.println("file:"+f.getName());
			FileUtils.deleteQuietly(f.getParentFile());
//			System.out.println("parent:"+f.getParentFile().getName());
		}
	 }
}
