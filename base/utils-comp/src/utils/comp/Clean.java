/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.comp;

import java.io.File;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author 97617
 */
public class Clean {
     public static final String path ="D:\\data\\m2";
    public static void main(String[] args) {
		Iterator<File> fs = FileUtils.iterateFiles(new File(path), new String[]{"lastUpdated","jar-in-progress"}, true);
		while(fs.hasNext()){
			File f = fs.next();
			System.out.println("file:"+f.getName());
			FileUtils.deleteQuietly(f.getParentFile());
//			System.out.println("parent:"+f.getParentFile().getName());
		}
	 }
}
